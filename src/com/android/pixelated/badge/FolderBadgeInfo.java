package com.android.pixelated.badge;

import com.android.pixelated.Utilities;

public class FolderBadgeInfo extends BadgeInfo {
    private int mNumNotifications;

    public FolderBadgeInfo() {
        super(null);
    }

    public void addBadgeInfo(BadgeInfo badgeInfo) {
        if (badgeInfo != null) {
            mNumNotifications += badgeInfo.getNotificationKeys().size();
            mNumNotifications = Utilities.boundToRange(mNumNotifications, 0, 999);
        }
    }

    public void subtractBadgeInfo(BadgeInfo badgeInfo) {
        if (badgeInfo != null) {
            mNumNotifications -= badgeInfo.getNotificationKeys().size();
            mNumNotifications = Utilities.boundToRange(mNumNotifications, 0, 999);
        }
    }

    @Override
    public int getNotificationCount() {
        return 0;
    }

    public boolean hasBadge() {
        return mNumNotifications > 0;
    }
}