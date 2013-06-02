package com.iv.rms.user;

public interface OwnerDAO {

    public Owner getOwner(User user);

    public Owner createNewOwner(User user);

    public Owner getOwner(String userId);

}
