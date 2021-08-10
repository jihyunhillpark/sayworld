package com.ssafy.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class RoomTagID implements Serializable {
    @Column(name="room_id")
    private long roomId;
    @Column(name="keyword_id")
    private int tagId;

    public RoomTagID(long roomId, int tagId) {
        this.roomId = roomId;
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o){
        if( this == o) return true;
        if( o instanceof RoomTagID ){
            RoomTagID key = (RoomTagID)o;
            return roomId == key.roomId && tagId == key.tagId;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Long.hashCode(roomId);
        hash = 31 * hash + Integer.hashCode(tagId);
        return hash;
    }
}