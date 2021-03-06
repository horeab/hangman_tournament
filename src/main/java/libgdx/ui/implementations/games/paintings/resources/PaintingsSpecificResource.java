package libgdx.ui.implementations.games.paintings.resources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.I18NBundle;

import libgdx.resources.SpecificResource;
import libgdx.ui.game.TournamentGame;

public enum PaintingsSpecificResource implements SpecificResource {

    // @formatter:off
    specific_labels("labels/labels", I18NBundle.class),

    campaign_level_0_0("campaign/l_0/level_0_0.png", Texture.class),
    campaign_level_0_1("campaign/l_0/level_0_1.png", Texture.class),
    campaign_level_0_2("campaign/l_0/level_0_2.png", Texture.class),
    campaign_level_0_3("campaign/l_0/level_0_3.png", Texture.class),
    campaign_level_0_4("campaign/l_0/level_0_4.png", Texture.class),

    title_clouds_background("title_clouds_background.png", Texture.class),
    title_clouds_background_en("title_clouds_background_en.png", Texture.class),
    title_clouds_background_de("title_clouds_background_de.png", Texture.class),
    title_clouds_background_fr("title_clouds_background_fr.png", Texture.class),
    title_clouds_background_ro("title_clouds_background_ro.png", Texture.class),
    title_rays("title_rays.png", Texture.class),
    background_texture("background_texture.png", Texture.class),
    ;

    // @formatter:on

    private String path;
    private Class<?> classType;

    PaintingsSpecificResource(String path, Class<?> classType) {
        this.path = path;
        this.classType = classType;
    }

    @Override
    public Class<?> getClassType() {
        return classType;
    }

    @Override
    public String getPath() {
        return TournamentGame.getInstance().getAppInfoService().getImplementationGameResourcesFolder() + path;
    }

}
