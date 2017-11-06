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

import android.support.annotation.AnimRes;

import com.izikode.izilib.chocopie.R;

public abstract class AnimatedFragment<F> extends PossessiveFragment<F> {

    @AnimRes
    public int getEnterAnimation() {
        return R.anim.yum_fadein_anim;
    }

    @AnimRes
    public int getExitAnimation() {
        return R.anim.yum_fadeout_anim;
    }
}