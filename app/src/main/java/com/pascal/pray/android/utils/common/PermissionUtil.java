package com.pascal.pray.android.utils.common;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

/**
 * Created by pascal on 12/30/16.
 */

public class PermissionUtil {
    public static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String CAMERA = Manifest.permission.CAMERA;

    public static void doWithPermissionsOrAsk(Activity activity, String[] Permissions, int requestCode, TaskNeedPermissions task) {
        if (activity == null) return;
        if (isVersionHigherThanM()) {
            if (doWithPermissions(activity, Permissions)) {
                task.run();
            } else {
                ActivityCompat.requestPermissions(activity, Permissions, requestCode);
            }
        } else {
            task.run();
        }
    }

    public static void doWithPermissionsOrAsk(Fragment fragment, String[] Permissions, int requestCode, TaskNeedPermissions task) {
        if (null == fragment || null == task) {
            return;
        }

        if (isVersionHigherThanM()) {
            if (doWithPermissions(fragment, Permissions)) {
                task.run();
            } else {
                fragment.requestPermissions(Permissions, requestCode);
            }
        } else {
            task.run();
        }
    }

    public static boolean doWithPermissions(Fragment fragment, String[] permissions) {
        if (null == fragment) {
            return false;
        }

        for (String permission : permissions) {
            //If user turn off permission from "Setting", "fragment.getContext()" must be return null
            if (null == fragment.getContext()) {
                return true;
            }
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(fragment.getContext(), permission)) {
                return false;
            }
        }
        return true;
    }

    public static boolean doWithPermissions(Activity activity, String[] Permissions) {
        for (int i = 0; i < Permissions.length; i++) {
            int readPicturePermission = ActivityCompat.checkSelfPermission(activity, Permissions[i]);
            if (readPicturePermission != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void doWithPermissions(Activity activity, String[] Permissions , TaskNeedPermissions task) {
        for (int i = 0; i < Permissions.length; i++) {
            int readPicturePermission = ActivityCompat.checkSelfPermission(activity, Permissions[i]);
            if (readPicturePermission != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        task.run();
    }

    public static void doWithPermissions(Fragment fragment, String[] permissions , TaskNeedPermissions task) {
        if (null == fragment || null == task) {
            return;
        }
        for (String permission : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(fragment.getContext(), permission)) {
                return;
            }
        }
        task.run();
    }

    public static void doWithPermissions(Activity activity, String[] Permissions , TaskNeedPermissions task , TaskWithoutPermissions noPermissionsTask) {
        for (int i = 0; i < Permissions.length; i++) {
            int readPicturePermission = ActivityCompat.checkSelfPermission(activity, Permissions[i]);
            if (readPicturePermission != PackageManager.PERMISSION_GRANTED) {
                noPermissionsTask.run();
                return;
            }
        }
        task.run();
    }

    public static void doWithPermissions(Fragment fragment, String[] permissions , TaskNeedPermissions task , TaskWithoutPermissions noPermissionsTask) {
        if (null == fragment || null == task || null == noPermissionsTask) {
            return;
        }

        for (String permission : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(fragment.getContext(), permission)) {
                noPermissionsTask.run();
                return;
            }
        }
        task.run();
    }

    public static boolean isVersionHigherThanM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public interface TaskNeedPermissions {
        void run();
    }

    public interface TaskWithoutPermissions {
        void run();
    }
}
