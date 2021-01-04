package com.codeforcommunity.processor;

import static org.jooq.generated.Tables.BLOCKS;
import static org.jooq.generated.Tables.NEIGHBORHOODS;
import static org.jooq.generated.Tables.RESERVATIONS;
import static org.jooq.impl.DSL.count;

import com.codeforcommunity.api.IMapProcessor;
import com.codeforcommunity.dto.map.*;
import com.codeforcommunity.enums.ReservationAction;
import com.codeforcommunity.logger.SLogger;
import io.vertx.core.json.JsonObject;
import java.util.List;
import java.util.stream.Collectors;

import org.jooq.*;
import org.jooq.generated.tables.records.BlocksRecord;
import org.jooq.generated.tables.records.NeighborhoodsRecord;

public class MapProcessorImpl implements IMapProcessor {

  private final SLogger logger = new SLogger(MapProcessorImpl.class);
  private final DSLContext db;

  public MapProcessorImpl(DSLContext db) {
    this.db = db;
  }

  /**
   * Create a corresponding BlockFeature from a BlocksRecord.
   */
  private BlockFeature blockFeatureFromRecord(BlocksRecord blocksRecord) {
    BlockFeatureProperties properties =
        new BlockFeatureProperties(
            blocksRecord.getId(), blocksRecord.getLat(), blocksRecord.getLng());
    try {
      JsonObject geometry = new JsonObject(blocksRecord.getGeometry().replaceAll("'", "\""));
      return new BlockFeature(properties, geometry);
    } catch (Exception e) {
      String errorMessage =
          String.format(
              "Exception thrown while processing conversion of geometry to JSON for block id [%d]",
              blocksRecord.getId());
      logger.error(errorMessage, e);
      throw e;
    }
  }

  /**
   * Given a neighborhoodId, return the percent of blocks that have been completed or are in QA in it
   * as an integer between 0 and 100.
   */
  private Integer getNeighborhoodCompletionPercentage(int neighborhoodId) {
    // This counts the number of blocks that are in the given neighborhood
    int totalNeighborhoodBlocks = db.select(count())
            .from(BLOCKS)
            .where(BLOCKS.NEIGHBORHOOD_ID.eq(neighborhoodId))
            .fetchOne(0, Integer.class);

    // This joins each block with their most recent reservations table entry
    Select<Record2<Integer, ReservationAction>> subquery = db.select(BLOCKS.ID, RESERVATIONS.ACTION_TYPE)
            .distinctOn(BLOCKS.ID)
            .from(BLOCKS)
            .join(RESERVATIONS).onKey()
            .where(BLOCKS.NEIGHBORHOOD_ID.eq(neighborhoodId))
            .orderBy(BLOCKS.ID, RESERVATIONS.PERFORMED_AT.desc());

    // This counts the number of rows in the above query that have their most recent reservation action as complete or qa
    int completedNeighborhoodBlocks = db.select(count())
            .from(subquery)
            .where(subquery.field(1, ReservationAction.class).in(ReservationAction.COMPLETE, ReservationAction.QA))
            .fetchOne(0, Integer.class);

    double completionPercent = (double) completedNeighborhoodBlocks / totalNeighborhoodBlocks;
    return (int) Math.floor(completionPercent * 100);
  }

  /**
   * Create a corresponding NeighborhoodFeature for a given neighborhoodsRecord
   */
  private NeighborhoodFeature neighborhoodFeatureFromRecord(
      NeighborhoodsRecord neighborhoodsRecord) {
    Integer neighborhoodCompletionPercentage = getNeighborhoodCompletionPercentage(neighborhoodsRecord.getId());
    NeighborhoodFeatureProperties properties =
        new NeighborhoodFeatureProperties(
            neighborhoodsRecord.getId(),
            neighborhoodsRecord.getNeighborhoodName(),
                neighborhoodCompletionPercentage,
            neighborhoodsRecord.getLat(),
            neighborhoodsRecord.getLng());
    try {
      JsonObject geometry = new JsonObject(neighborhoodsRecord.getGeometry().replaceAll("'", "\""));
      return new NeighborhoodFeature(properties, geometry);
    } catch (Exception e) {
      String errorMessage =
          String.format(
              "Exception thrown while processing conversion of geometry to JSON for neighborhood id [%d]",
              neighborhoodsRecord.getId());
      logger.error(errorMessage, e);
      throw e;
    }
  }

  @Override
  public BlockGeoResponse getBlockGeoJson() {
    List<BlockFeature> features =
        this.db.selectFrom(BLOCKS).stream()
            .map(this::blockFeatureFromRecord)
            .collect(Collectors.toList());
    return new BlockGeoResponse(features);
  }

  @Override
  public NeighborhoodGeoResponse getNeighborhoodGeoJson() {
    List<NeighborhoodFeature> features =
        this.db.selectFrom(NEIGHBORHOODS).stream()
            .map(this::neighborhoodFeatureFromRecord)
            .collect(Collectors.toList());
    return new NeighborhoodGeoResponse(features);
  }
}