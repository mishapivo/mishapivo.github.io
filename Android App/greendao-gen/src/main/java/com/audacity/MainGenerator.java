package com.audacity;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class MainGenerator {
    public static void main(String[] args)  throws Exception {

        //place where db folder will be created inside the project folder
        Schema schema = new Schema(1,"com.audacityit.audacity.greendao.db");

        //module for slide bar data module
        Entity slideEntity = schema.addEntity("SlideBarGR");
        slideEntity.addIdProperty().autoincrement().primaryKey();
        slideEntity.addStringProperty("background");
        slideEntity.addStringProperty("logo");
        slideEntity.addStringProperty("phone");
        slideEntity.addStringProperty("email");
        slideEntity.addStringProperty("web");


        //module for splash data module
        Entity splashEntity = schema.addEntity("SplashGR");
        splashEntity.addIdProperty().autoincrement().primaryKey();
        splashEntity.addStringProperty("logo");
        splashEntity.addStringProperty("bgcolor");

        //module for overviewcontent
        Entity overviewcontentEntity = schema.addEntity("OverviewcontentGR");
        overviewcontentEntity.addIdProperty().autoincrement().primaryKey();
        overviewcontentEntity.addStringProperty("title");
        overviewcontentEntity.addStringProperty("background");
        overviewcontentEntity.addStringProperty("logo");
        overviewcontentEntity.addStringProperty("moto");
        overviewcontentEntity.addStringProperty("count1text");
        overviewcontentEntity.addStringProperty("count1no");
        overviewcontentEntity.addStringProperty("count2text");
        overviewcontentEntity.addStringProperty("count2no");
        overviewcontentEntity.addStringProperty("count3text");
        overviewcontentEntity.addStringProperty("count3no");

        //module for overviewitems
        Entity overview = schema.addEntity("OverviewGR");
        overview.addIdProperty().autoincrement().primaryKey();

        Entity overviewItems  = schema.addEntity("OverviewitemsGR");
        overviewItems.addIdProperty().autoincrement().primaryKey();
        overviewItems.addStringProperty("title");
        overviewItems.addStringProperty("description");
        overviewItems.addStringProperty("icon");
        Property overPro = overviewItems.addLongProperty("overview_id").notNull().getProperty();
        overviewItems.addToOne(overview, overPro);
        overview.addToMany(overviewItems, overPro);

        ///module for home
        Entity homeEntity = schema.addEntity("HomeGR");
        homeEntity.addIdProperty().autoincrement().primaryKey();

        Entity home_Entity = schema.addEntity("HomeGR_");
        home_Entity.addIdProperty().autoincrement().primaryKey();
        home_Entity.addStringProperty("title");
        home_Entity.addStringProperty("background");
        Property homePro = home_Entity.addLongProperty("home_id").notNull().getProperty();
        home_Entity.addToOne(homeEntity, homePro);
        homeEntity.addToMany(home_Entity, homePro);


        ///module for Portfolio
        Entity portfolioEntity = schema.addEntity("PortfolioGR");
        portfolioEntity.addIdProperty().autoincrement().primaryKey();

        Entity portfolio_Entity = schema.addEntity("PortfolioGR_");
        portfolio_Entity.addIdProperty().autoincrement().primaryKey();
        portfolio_Entity.addStringProperty("title");
        portfolio_Entity.addStringProperty("image");

        Entity tagEntity1 = schema.addEntity("PTagGR");
        tagEntity1.addIdProperty().autoincrement().primaryKey();
        tagEntity1.addStringProperty("tag");
        tagEntity1.addStringProperty("url");
        Property portfolio_Pro = tagEntity1.addLongProperty("tag_id").notNull().getProperty();
        tagEntity1.addToOne(portfolio_Entity, portfolio_Pro);
        portfolio_Entity.addToMany(tagEntity1, portfolio_Pro);

        Property portfolioPro = portfolio_Entity.addLongProperty("portfolio_id").notNull().getProperty();
        portfolio_Entity.addToOne(portfolioEntity, portfolioPro);
        portfolioEntity.addToMany(portfolio_Entity, portfolioPro);


        //module for client data
        Entity clientEntity = schema.addEntity("ClientGR");
        clientEntity.addIdProperty().autoincrement().primaryKey();

        Entity client_Entity = schema.addEntity("ClientGR_");
        client_Entity.addIdProperty().autoincrement().primaryKey();
        client_Entity.addStringProperty("name");
        client_Entity.addStringProperty("logo");
        client_Entity.addStringProperty("company");
        client_Entity.addStringProperty("country");

        Entity tagEntity = schema.addEntity("ClientTag");
        tagEntity.addIdProperty().autoincrement().primaryKey();
        tagEntity.addStringProperty("tag");
        tagEntity.addStringProperty("url");
        Property clientPro = tagEntity.addLongProperty("tag_id").notNull().getProperty();
        tagEntity.addToOne(client_Entity, clientPro);
        client_Entity.addToMany(tagEntity, clientPro);

        Property client_Pro = client_Entity.addLongProperty("client_id").notNull().getProperty();
        client_Entity.addToOne(clientEntity, client_Pro);
        clientEntity.addToMany(client_Entity, client_Pro);


        //module for getstarted data
        Entity getstarted_Entity = schema.addEntity("GetstartedGR");
        getstarted_Entity.addIdProperty().autoincrement().primaryKey();
        getstarted_Entity.addStringProperty("range1");
        getstarted_Entity.addStringProperty("range2");
        getstarted_Entity.addStringProperty("range3");
        getstarted_Entity.addStringProperty("range4");
        getstarted_Entity.addStringProperty("phone");
        getstarted_Entity.addStringProperty("email");
        getstarted_Entity.addStringProperty("web");

        Entity gTagEntity = schema.addEntity("GetstartedTag");
        gTagEntity.addIdProperty().autoincrement().primaryKey();
        gTagEntity.addStringProperty("tag");
        Property startPro = gTagEntity.addLongProperty("tag_id").notNull().getProperty();
        gTagEntity.addToOne(getstarted_Entity, startPro);
        getstarted_Entity.addToMany(gTagEntity, startPro);


        ///module for team
        Entity teamEntity = schema.addEntity("TeamGR");
        teamEntity.addIdProperty().autoincrement().primaryKey();

        Entity team_Entity = schema.addEntity("TeamGR_");
        team_Entity.addIdProperty().autoincrement().primaryKey();
        team_Entity.addStringProperty("name");
        team_Entity.addStringProperty("designation");
        team_Entity.addStringProperty("photo");
        Property teamPro = team_Entity.addLongProperty("team_id").notNull().getProperty();
        team_Entity.addToOne(teamEntity, teamPro);
        teamEntity.addToMany(team_Entity, teamPro);


        ///module for methodology
        Entity methodologyEntity = schema.addEntity("MethodologyGR");
        methodologyEntity.addIdProperty().autoincrement().primaryKey();

        Entity methodology_Entity = schema.addEntity("MethodologyGR_");
        methodology_Entity.addIdProperty().autoincrement().primaryKey();
        methodology_Entity.addStringProperty("background");
        methodology_Entity.addStringProperty("logo");

        Entity itemEntiry = schema.addEntity("MethodologyItemGR");
        itemEntiry.addIdProperty().autoincrement().primaryKey();
        itemEntiry.addStringProperty("title");
        itemEntiry.addStringProperty("details");
        itemEntiry.addStringProperty("icon");
        Property itemPro = itemEntiry.addLongProperty("item_id").notNull().getProperty();
        itemEntiry.addToOne(methodology_Entity, itemPro);
        methodology_Entity.addToMany(itemEntiry, itemPro);

        Property methodologyPro = methodology_Entity.addLongProperty("methodology_id").notNull().getProperty();
        methodology_Entity.addToOne(methodologyEntity, methodologyPro);
        methodologyEntity.addToMany(methodology_Entity, methodologyPro);

        ///module for fqa
        Entity fqaEntity = schema.addEntity("FqaGR");
        fqaEntity.addIdProperty().autoincrement().primaryKey();

        Entity fqa_Entity = schema.addEntity("FqaGR_");
        fqa_Entity.addIdProperty().autoincrement().primaryKey();
        fqa_Entity.addStringProperty("question");
        fqa_Entity.addStringProperty("answer");
        Property fqaPro = fqa_Entity.addLongProperty("fqa_id").notNull().getProperty();
        fqa_Entity.addToOne(fqaEntity, fqaPro);
        fqaEntity.addToMany(fqa_Entity, fqaPro);

        ///module for fqa
        Entity testimonialEntity = schema.addEntity("TestimonialGR");
        testimonialEntity.addIdProperty().autoincrement().primaryKey();

        Entity testimonial_Entity = schema.addEntity("TestimonialGR_");
        testimonial_Entity.addIdProperty().autoincrement().primaryKey();
        testimonial_Entity.addStringProperty("name");
        testimonial_Entity.addStringProperty("image");
        testimonial_Entity.addStringProperty("project");
        testimonial_Entity.addStringProperty("url");
        testimonial_Entity.addStringProperty("feedback");
        Property ftestimonialPro = testimonial_Entity.addLongProperty("testimonial_id").notNull().getProperty();
        testimonial_Entity.addToOne(testimonialEntity, ftestimonialPro);
        testimonialEntity.addToMany(testimonial_Entity, ftestimonialPro);


        ///module for social
        Entity socialEntity = schema.addEntity("SocialGR");
        socialEntity.addIdProperty().autoincrement().primaryKey();

        Entity social_Entity = schema.addEntity("SocialGR_");
        social_Entity.addIdProperty().autoincrement().primaryKey();
        social_Entity.addStringProperty("name");
        social_Entity.addStringProperty("image");
        social_Entity.addStringProperty("url");
        Property socialPro = social_Entity.addLongProperty("social_id").notNull().getProperty();
        social_Entity.addToOne(socialEntity, socialPro);
        socialEntity.addToMany(social_Entity, socialPro);


        new DaoGenerator().generateAll(schema, "./app/src/main/java");

    }

}
