package com.izikode.izilib.chocopie.fragment.core;

/*
 * Copyright 2017 Fanie Veizis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.izikode.izilib.chocopie.delegate.LifecycleDelegates;

public abstract class LifecycleFragment<F> extends AnimatedFragment<F> implements LifecycleDelegates<F> {

    private void handleCreation(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
        initialized = bundle != null;

        if (getRootView() == null) {
            setRootView(inflater.inflate(getContentView(), container, false));

            getRootView().getRootView().setClickable(true);
            Integer backgroundColor = getBackgroundColor();

            if (backgroundColor != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    getRootView().getRootView().setBackgroundColor(getContext().getColor(backgroundColor));
                } else {
                    getRootView().getRootView().setBackgroundColor(getContext().getResources().getColor(backgroundColor));
                }
            }
        }

        init();

        loadViews();
        create();

        if (!initialized) {
            initialize();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        handleCreation(inflater, container, savedInstanceState);
        return getRootView();
    }

    @Deprecated
    @Override
    public void onStart() {
        beforeAppearing();
        super.onStart();
    }

    @Deprecated
    @Override
    public void onResume() {
        super.onResume();
        afterAppearing();

        if (initialized) {
            surfaceRetainables();
        }

        if (!initialized) {
            initialized = true;
        }
    }

    @Deprecated
    @Override
    public void onPause() {
        submergeRetainables();
        beforeHiding();
        super.onPause();
    }

    @Deprecated
    @Override
    public void onStop() {
        super.onStop();
        afterHiding();
    }

    @Deprecated
    @Override
    public void onDestroy() {
        beforeDismissing();
        super.onDestroy();
    }

    @CallSuper
    @Override
    public void onRestoredInstanceReady(@NonNull F restoredInstance) {
        restore(restoredInstance);
        persistentContainer.clearUnboxables();
    }
}
