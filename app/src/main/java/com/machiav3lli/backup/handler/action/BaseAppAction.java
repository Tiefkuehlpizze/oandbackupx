/*
 * OAndBackupX: open-source apps backup and restore app.
 * Copyright (C) 2020  Antonios Hazim
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.machiav3lli.backup.handler.action;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.machiav3lli.backup.Constants;
import com.machiav3lli.backup.handler.ShellHandler;
import com.topjohnwu.superuser.Shell;

import java.util.Arrays;
import java.util.List;

public abstract class BaseAppAction {
    public static final int MODE_UNSET = 0;
    public static final int MODE_APK = 1;
    public static final int MODE_DATA = 2;
    public static final int MODE_BOTH = 3;
    protected static final String BACKUP_DIR_DATA = "data";
    protected static final String BACKUP_DIR_DEVICE_PROTECTED_FILES = "device_protected_files";
    protected static final String BACKUP_DIR_EXTERNAL_FILES = "external_files";
    protected static final String BACKUP_DIR_OBB_FILES = "obb_files";
    protected static final List<String> DATA_EXCLUDED_DIRS = Arrays.asList("cache", "code_cache", "lib");
    private static final String TAG = Constants.classTag(".BaseAppAction");
    private final ShellHandler shell;
    private final Context context;

    protected BaseAppAction(Context context, ShellHandler shell) {
        this.context = context;
        this.shell = shell;
    }

    protected static String extractErrorMessage(Shell.Result shellResult) {
        // if stderr does not say anything, try stdout
        List<String> err = shellResult.getErr().isEmpty() ? shellResult.getOut() : shellResult.getErr();
        if (err.isEmpty()) {
            return "Unknown Error";
        }
        return err.get(err.size() - 1);
    }

    protected ShellHandler getShell() {
        return this.shell;
    }

    public String getBackupArchiveFilename(String what, boolean isEncrypted) {
        return what + ".tar.gz" + (isEncrypted ? ".enc" : "");
    }

    public String prependUtilbox(String command) {
        return String.format("%s %s", this.shell.getUtilboxPath(), command);
    }

    protected Context getContext() {
        return this.context;
    }

    public abstract static class AppActionFailedException extends Exception {
        protected AppActionFailedException(String message){
            super(message);
        }
        protected AppActionFailedException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @SuppressLint("DefaultLocale")
    public void prePackage(String packageName) {
        try {
            ApplicationInfo applicationInfo = this.context.getPackageManager().getApplicationInfo(packageName, 0);
            Log.i(BaseAppAction.TAG, String.format("package %s uid %d", packageName, applicationInfo.uid));
            /**
            if (applicationInfo.uid == 1000) {
                Log.w(BaseAppAction.TAG, "Requested to kill processes of UID 1000. Refusing to kill system's processes!");
                return;
            }
            /**/
            ShellHandler.runAsRoot(String.format("am force-stop %s", packageName));
            //ShellHandler.runAsRoot(String.format("pm suspend --user %d %s", applicationInfo.uid, packageName)); // looks like it does not work, wrong permissions
            //ShellHandler.runAsRoot(String.format("pm suspend %s", packageName)); // when used alone, kind of disables the app
            //ShellHandler.runAsRoot(String.format("pm suspend %s ; pm unsuspend %s", packageName, packageName)); // hopefully stops the app but keeps it available
            //ShellHandler.runAsRoot(String.format("ps -o PID -u %d | grep -v PID | xargs kill -TERM", applicationInfo.uid)); // may be too low level and too hard (unless it's mapped by Android on the app lifecycle)
            //ShellHandler.runAsRoot(String.format("ps -o PID -u %d | grep -v PID | xargs kill -STOP", applicationInfo.uid)); // hold your breath (hoepfully files don't change afterwards)
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(BaseAppAction.TAG, packageName + " does not exist. Cannot preprocess!");
        } catch (ShellHandler.ShellCommandFailedException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("DefaultLocale")
    public void postPackage(String packageName) {
        try {
            ApplicationInfo applicationInfo = this.context.getPackageManager().getApplicationInfo(packageName, 0);
            /**
            if (applicationInfo.uid == 1000) {
                Log.w(BaseAppAction.TAG, "Requested to kill processes of UID 1000. Refusing to kill system's processes!");
                return;
            }
            /**/
            //ShellHandler.runAsRoot(String.format("pm unsuspend --user %d %s", applicationInfo.uid, packageName)); // looks like it does not work, wrong permissions
            //ShellHandler.runAsRoot(String.format("pm unsuspend %s", packageName)); // better done directly after suspend (before backup), when STOP is also used
            //ShellHandler.runAsRoot(String.format("ps -o PID -u %d | grep -v PID | xargs kill -CONT")); // continue everything that was stopped
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(BaseAppAction.TAG, packageName + " does not exist. Cannot postprocess!");
        //} catch (ShellHandler.ShellCommandFailedException e) {
        //    e.printStackTrace();
        }
    }
}
