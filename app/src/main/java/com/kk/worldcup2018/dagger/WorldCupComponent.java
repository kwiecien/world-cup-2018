package com.kk.worldcup2018.dagger;

import com.kk.worldcup2018.view.TeamsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = WorldCupModule.class)
public interface WorldCupComponent {

    void inject(TeamsFragment fragment);

}
