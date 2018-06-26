package com.kk.worldcup2018.dagger;

import com.kk.worldcup2018.view.fixtures.FixturesFragment;
import com.kk.worldcup2018.view.groups.GroupsFragment;
import com.kk.worldcup2018.view.teams.TeamsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = WorldCupModule.class)
public interface WorldCupComponent {

    void inject(TeamsFragment teamsFragment);

    void inject(FixturesFragment fixturesFragment);

    void inject(GroupsFragment groupsFragment);

}
