/**
 * Copyright 2015 Anthony Restaino

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 http://www.apache.org/licenses/LICENSE-2.0
 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 either express or implied. See the License for the specific language governing
 permissions and limitations under the License.
 */
package com.jinyuankeji.yxm.findhuo.lottery.runtimepermissions;

import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public abstract class PermissionsResultAction {

  private static final String TAG = PermissionsResultAction.class.getSimpleName();
  private final Set<String> mPermissions = new HashSet<String>(1);
  private Looper mLooper = Looper.getMainLooper();


  public PermissionsResultAction() {}


  @SuppressWarnings("unused")
  public PermissionsResultAction(@NonNull Looper looper) {mLooper = looper;}


  public abstract void onGranted();


  public abstract void onDenied(String permission);

  @SuppressWarnings({"WeakerAccess", "SameReturnValue"})
  public synchronized boolean shouldIgnorePermissionNotFound(String permission) {
    Log.d(TAG, "Permission not found: " + permission);
    return true;
  }

  @SuppressWarnings("WeakerAccess")
  @CallSuper
  protected synchronized final boolean onResult(final @NonNull String permission, int result) {
    if (result == PackageManager.PERMISSION_GRANTED) {
      return onResult(permission, Permissions.GRANTED);
    } else {
      return onResult(permission, Permissions.DENIED);
    }

  }


  @SuppressWarnings("WeakerAccess")
  @CallSuper
  protected synchronized final boolean onResult(final @NonNull String permission, Permissions result) {
    mPermissions.remove(permission);
    if (result == Permissions.GRANTED) {
      if (mPermissions.isEmpty()) {
        new Handler(mLooper).post(new Runnable() {
          @Override
          public void run() {
            onGranted();
          }
        });
        return true;
      }
    } else if (result == Permissions.DENIED) {
      new Handler(mLooper).post(new Runnable() {
        @Override
        public void run() {
          onDenied(permission);
        }
      });
      return true;
    } else if (result == Permissions.NOT_FOUND) {
      if (shouldIgnorePermissionNotFound(permission)) {
        if (mPermissions.isEmpty()) {
          new Handler(mLooper).post(new Runnable() {
            @Override
            public void run() {
              onGranted();
            }
          });
          return true;
        }
      } else {
        new Handler(mLooper).post(new Runnable() {
          @Override
          public void run() {
            onDenied(permission);
          }
        });
        return true;
      }
    }
    return false;
  }


  @SuppressWarnings("WeakerAccess")
  @CallSuper
  protected synchronized final void registerPermissions(@NonNull String[] perms) {
    Collections.addAll(mPermissions, perms);
  }
}