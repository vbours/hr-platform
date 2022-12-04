package com.boursinos.hrplatform.model.storage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Contains information of objects.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ObjectInfos implements Comparable<ObjectInfos> {
  private String timestamp;
  private long size;
  private String objectName;

  /**
   * Contains information of object.
   *
   * @param timestamp timestamp
   * @param size size
   * @param objectName objectName
   */
  public ObjectInfos(String timestamp, long size, String objectName) {
    this.timestamp = timestamp;
    this.size = size;
    this.objectName = objectName;
  }

  @Override
  public int compareTo(ObjectInfos o) {
    return this.getTimestamp().compareTo(o.getTimestamp());
  }

}
