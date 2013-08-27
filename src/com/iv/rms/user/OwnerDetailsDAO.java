package com.iv.rms.user;

public interface OwnerDetailsDAO {

    public OwnerDetails load(String userId);

    public OwnerDetails save(OwnerDetails details);

}
