/*
 *  Copyright 2015 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.mibaldi.loanmanagement.base.baseMosby.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import butterknife.ButterKnife;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;
import com.mibaldi.loanmanagement.base.BasePresenter;


import butterknife.Unbinder;
import icepick.Icepick;

/**
 * @author Hannes Dorfmann
 */
public abstract class BaseLceActivity<CV extends View, M, V extends MvpLceView<M>, P extends BasePresenter<V>> extends MvpLceViewStateActivity<CV, M, V, P> {

  private Unbinder bind;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Icepick.restoreInstanceState(this, savedInstanceState);
  }

  @Override public void onContentChanged() {
    super.onContentChanged();
    bind = ButterKnife.bind(this);
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    Icepick.saveInstanceState(this, outState);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    bind.unbind();
  }

  public Intent getCallingIntent(Context context) {
    return new Intent(context, context.getClass());
  }
}
