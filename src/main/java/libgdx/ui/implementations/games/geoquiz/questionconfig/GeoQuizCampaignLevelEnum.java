package libgdx.ui.implementations.games.geoquiz.questionconfig;

import libgdx.ui.constants.game.campaign.CampaignLevel;

public enum GeoQuizCampaignLevelEnum implements CampaignLevel {

    //This order is displayed on the campaign screen
    LEVEL_0_0,
    LEVEL_0_1,
    LEVEL_0_2,
    LEVEL_0_3,
    LEVEL_0_4,
    LEVEL_0,
    ////
    LEVEL_1_0,
    LEVEL_1_1,
    LEVEL_1_2,
    LEVEL_1_3,
    LEVEL_1_4,
    LEVEL_1,
    ////
    LEVEL_2_0,
    LEVEL_2_1,
    LEVEL_2_2,
    LEVEL_2_3,
    LEVEL_2_4,
    LEVEL_2,
    ////
    LEVEL_3_0,
    LEVEL_3_1,
    LEVEL_3_2,
    LEVEL_3_3,
    LEVEL_3_4,
    LEVEL_3;

    @Override
    public int getIndex() {
        return ordinal();
    }

    @Override
    public String getName(){
        return name();
    }
}
