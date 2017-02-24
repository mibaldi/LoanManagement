package com.mibaldi.loanmanagement.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mibaldi.loanmanagement.R;
import com.mibaldi.loanmanagement.base.baseMosby.fragment.BaseMVPFragment;
import com.mibaldi.loanmanagement.ui.presenters.thirdActivity.firstThirdFragment.FirstThirdFragmentPresenter;
import com.mibaldi.loanmanagement.ui.presenters.thirdActivity.ThirdActivityComponent;
import com.mibaldi.loanmanagement.ui.presenters.thirdActivity.secondThirdFragment.SecondThirdFragmentPresenter;
import com.mibaldi.loanmanagement.ui.views.thirdActivity.FirstThirdFragmentView;
import com.mibaldi.loanmanagement.ui.views.thirdActivity.SecondThirdFragmentView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SecondThirdFragment extends BaseMVPFragment<SecondThirdFragmentPresenter,SecondThirdFragmentView> implements SecondThirdFragmentView{
    private ThirdActivityComponent component;
    private Unbinder unbind;


    @Inject
    public SecondThirdFragment() {
        setRetainInstance(true);
    }

    public static SecondThirdFragment newInstance() {
        SecondThirdFragment fragment = new SecondThirdFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.init(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        component = getComponent(ThirdActivityComponent.class);
        component.inject(this);
        View view = inflater.inflate(R.layout.fragment_second_third,container,false);
        unbind = ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }

    @Override
    public SecondThirdFragmentPresenter createPresenter() {
        return component.secondThirdFragmentPresenter();
    }

    @Override
    public void showMessage(String message) {

    }
    @OnClick(R.id.btn_changefragment)
    public void nextStep(){
        presenter.nextStep();
    }
}
