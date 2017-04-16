package com.audacityit.audacity.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.audacityit.audacity.Constants;
import com.audacityit.audacity.api.response_layer.all.Data;
import com.audacityit.audacity.api.response_layer.client.Client_;
import com.audacityit.audacity.api.response_layer.faq.Faq_;
import com.audacityit.audacity.api.response_layer.getstarted.Getstarted;
import com.audacityit.audacity.api.response_layer.home.Home_;
import com.audacityit.audacity.api.response_layer.methodology.Methodology;
import com.audacityit.audacity.api.response_layer.methodology.Methodologycontent;
import com.audacityit.audacity.api.response_layer.methodology.Methodologyitem;
import com.audacityit.audacity.api.response_layer.overview.Overview;
import com.audacityit.audacity.api.response_layer.overview.Overviewcontent;
import com.audacityit.audacity.api.response_layer.overview.Overviewitem;
import com.audacityit.audacity.api.response_layer.portfolio.Portfolio_;
import com.audacityit.audacity.api.response_layer.portfolio.Tag;
import com.audacityit.audacity.api.response_layer.sidebar.Sidebar;
import com.audacityit.audacity.api.response_layer.social.Social_;
import com.audacityit.audacity.api.response_layer.splash.Splash;
import com.audacityit.audacity.api.response_layer.team.Team_;
import com.audacityit.audacity.api.response_layer.testimonial.Testimonial_;
import com.audacityit.audacity.greendao.db.ClientGR;
import com.audacityit.audacity.greendao.db.ClientGRDao;
import com.audacityit.audacity.greendao.db.ClientGR_;
import com.audacityit.audacity.greendao.db.ClientGR_Dao;
import com.audacityit.audacity.greendao.db.ClientTag;
import com.audacityit.audacity.greendao.db.ClientTagDao;
import com.audacityit.audacity.greendao.db.DaoMaster;
import com.audacityit.audacity.greendao.db.DaoSession;
import com.audacityit.audacity.greendao.db.FqaGR;
import com.audacityit.audacity.greendao.db.FqaGRDao;
import com.audacityit.audacity.greendao.db.FqaGR_;
import com.audacityit.audacity.greendao.db.FqaGR_Dao;
import com.audacityit.audacity.greendao.db.GetstartedGR;
import com.audacityit.audacity.greendao.db.GetstartedGRDao;
import com.audacityit.audacity.greendao.db.GetstartedTag;
import com.audacityit.audacity.greendao.db.GetstartedTagDao;
import com.audacityit.audacity.greendao.db.HomeGR;
import com.audacityit.audacity.greendao.db.HomeGRDao;
import com.audacityit.audacity.greendao.db.HomeGR_;
import com.audacityit.audacity.greendao.db.HomeGR_Dao;
import com.audacityit.audacity.greendao.db.MethodologyGR;
import com.audacityit.audacity.greendao.db.MethodologyGRDao;
import com.audacityit.audacity.greendao.db.MethodologyGR_;
import com.audacityit.audacity.greendao.db.MethodologyGR_Dao;
import com.audacityit.audacity.greendao.db.MethodologyItemGR;
import com.audacityit.audacity.greendao.db.MethodologyItemGRDao;
import com.audacityit.audacity.greendao.db.OverviewGR;
import com.audacityit.audacity.greendao.db.OverviewGRDao;
import com.audacityit.audacity.greendao.db.OverviewcontentGR;
import com.audacityit.audacity.greendao.db.OverviewcontentGRDao;
import com.audacityit.audacity.greendao.db.OverviewitemsGR;
import com.audacityit.audacity.greendao.db.OverviewitemsGRDao;
import com.audacityit.audacity.greendao.db.PTagGR;
import com.audacityit.audacity.greendao.db.PTagGRDao;
import com.audacityit.audacity.greendao.db.PortfolioGR;
import com.audacityit.audacity.greendao.db.PortfolioGRDao;
import com.audacityit.audacity.greendao.db.PortfolioGR_;
import com.audacityit.audacity.greendao.db.PortfolioGR_Dao;
import com.audacityit.audacity.greendao.db.SlideBarGR;
import com.audacityit.audacity.greendao.db.SlideBarGRDao;
import com.audacityit.audacity.greendao.db.SocialGR;
import com.audacityit.audacity.greendao.db.SocialGRDao;
import com.audacityit.audacity.greendao.db.SocialGR_;
import com.audacityit.audacity.greendao.db.SocialGR_Dao;
import com.audacityit.audacity.greendao.db.SplashGR;
import com.audacityit.audacity.greendao.db.SplashGRDao;
import com.audacityit.audacity.greendao.db.TeamGR;
import com.audacityit.audacity.greendao.db.TeamGRDao;
import com.audacityit.audacity.greendao.db.TeamGR_;
import com.audacityit.audacity.greendao.db.TeamGR_Dao;
import com.audacityit.audacity.greendao.db.TestimonialGR;
import com.audacityit.audacity.greendao.db.TestimonialGRDao;
import com.audacityit.audacity.greendao.db.TestimonialGR_;
import com.audacityit.audacity.greendao.db.TestimonialGR_Dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hasmath on 7/6/2016.
 */
public class DBHelper {

    SQLiteDatabase db;
    Context mContext;
    DaoSession masterSession;
    SplashGRDao splashGRDao;
    OverviewcontentGRDao overviewcontentGRDao;
    OverviewGRDao overviewGRDao;
    OverviewitemsGRDao overviewitemsGRDao;
    HomeGRDao homeGRDao;
    HomeGR_Dao homeGR_dao;
    PortfolioGRDao portfolioGRDao;
    PortfolioGR_Dao portfolioGR_dao;
    PTagGRDao pTagGRDao;
    TeamGRDao teamGRDao;
    TeamGR_Dao teamGR_dao;
    SocialGR_Dao socialGR_dao;
    SocialGRDao socialGRDao;
    FqaGRDao fqaGRDao;
    FqaGR_Dao fqaGR_dao;
    ClientGRDao clientGRDao;
    ClientGR_Dao clientGR_dao;
    ClientTagDao clientTagDao;
    TestimonialGRDao testimonialGRDao;
    TestimonialGR_Dao testimonialGR_dao;
    GetstartedGRDao getstartedGRDao;
    GetstartedTagDao getstartedTagDao;
    MethodologyGR_Dao methodologyGR_dao;
    MethodologyGRDao methodologyGRDao;
    MethodologyItemGRDao methodologyItemGRDao;
    SlideBarGRDao slideBarGRDao;
    public void setupDb(Context context){
        DaoMaster.DevOpenHelper masterHelper = new DaoMaster.DevOpenHelper(context, Constants.DATABASE_NAME, null); //create database db file if not exist
        db = masterHelper.getWritableDatabase();  //get the created database db file
        DaoMaster master = new DaoMaster(db);//create masterDao
        DaoSession masterSession=master.newSession(); //Creates Session session
        mContext = context;
        splashGRDao = masterSession.getSplashGRDao();
        overviewcontentGRDao = masterSession.getOverviewcontentGRDao();
        overviewGRDao = masterSession.getOverviewGRDao();
        overviewitemsGRDao = masterSession.getOverviewitemsGRDao();
        homeGR_dao = masterSession.getHomeGR_Dao();
        homeGRDao = masterSession.getHomeGRDao();
        portfolioGR_dao = masterSession.getPortfolioGR_Dao();
        portfolioGRDao = masterSession.getPortfolioGRDao();
        pTagGRDao = masterSession.getPTagGRDao();
        socialGR_dao = masterSession.getSocialGR_Dao();
        socialGRDao = masterSession.getSocialGRDao();
        teamGR_dao = masterSession.getTeamGR_Dao();
        teamGRDao = masterSession.getTeamGRDao();
        fqaGR_dao = masterSession.getFqaGR_Dao();
        fqaGRDao = masterSession.getFqaGRDao();
        clientGRDao = masterSession.getClientGRDao();
        clientTagDao = masterSession.getClientTagDao();
        clientGR_dao = masterSession.getClientGR_Dao();
        testimonialGR_dao = masterSession.getTestimonialGR_Dao();
        testimonialGRDao = masterSession.getTestimonialGRDao();
        getstartedGRDao = masterSession.getGetstartedGRDao();
        getstartedTagDao  = masterSession.getGetstartedTagDao();
        methodologyGR_dao = masterSession.getMethodologyGR_Dao();
        methodologyGRDao = masterSession.getMethodologyGRDao();
        methodologyItemGRDao = masterSession.getMethodologyItemGRDao();
        slideBarGRDao = masterSession.getSlideBarGRDao();
    }

    //clear all from db
    public void deleteCPAll(){
        Log.d(mContext+"--delete start-","---");
        splashGRDao.deleteAll();
        overviewcontentGRDao.deleteAll();
        overviewitemsGRDao.deleteAll();
        overviewGRDao.deleteAll();
        homeGRDao.deleteAll();
        homeGR_dao.deleteAll();
        portfolioGR_dao.deleteAll();
        portfolioGRDao.deleteAll();
        pTagGRDao.deleteAll();
        teamGR_dao.deleteAll();
        teamGRDao.deleteAll();
        socialGR_dao.deleteAll();
        socialGRDao.deleteAll();
        fqaGR_dao.deleteAll();
        fqaGRDao.deleteAll();
        clientGRDao.deleteAll();
        clientTagDao.deleteAll();
        clientGR_dao.deleteAll();
        testimonialGRDao.deleteAll();
        testimonialGR_dao.deleteAll();
        getstartedGRDao.deleteAll();
        getstartedTagDao.deleteAll();
        methodologyItemGRDao.deleteAll();
        methodologyGRDao.deleteAll();
        methodologyGR_dao.deleteAll();
        slideBarGRDao.deleteAll();
        Log.d(mContext+"--delete end-","---");
    }

    public void insertCPAll(Data data) {
        //insert splash
        Log.d(mContext+"--insert start-","---");
        SplashGR splashGR = new SplashGR();

        splashGR.setLogo(data.getSplash().getLogo());
        splashGR.setBgcolor(data.getSplash().getBgcolor());
        splashGRDao.insert(splashGR);

        //insert slide bar
        SlideBarGR slideBarGR = new SlideBarGR();
        Sidebar sidebar = data.getSidebar();
        slideBarGR.setId(1L);
        slideBarGR.setBackground(sidebar.getBackground());
        slideBarGR.setEmail(sidebar.getEmail());
        slideBarGR.setLogo(sidebar.getLogo());
        slideBarGR.setPhone(sidebar.getPhone());
        slideBarGR.setWeb(sidebar.getWeb());
        slideBarGRDao.insert(slideBarGR);

        //insert overviewcontent

        OverviewcontentGR overviewcontentRs = new OverviewcontentGR();
        Overviewcontent overviewcontent = data.getOverview().getOverviewcontent();
        overviewcontentRs.setId(1L);

        overviewcontentRs.setTitle(overviewcontent.getTitle());
        overviewcontentRs.setBackground(overviewcontent.getBackground());
        overviewcontentRs.setLogo(overviewcontent.getLogo());
        overviewcontentRs.setMoto(overviewcontent.getMoto());
        overviewcontentRs.setCount1text(overviewcontent.getCount1text());
        overviewcontentRs.setCount1no(overviewcontent.getCount1no());
        overviewcontentRs.setCount2text(overviewcontent.getCount2text());
        overviewcontentRs.setCount2no(overviewcontent.getCount2no());
        overviewcontentRs.setCount3text(overviewcontent.getCount3text());
        overviewcontentRs.setCount3no(overviewcontent.getCount3no());
        overviewcontentGRDao.insert(overviewcontentRs);

        //insert overviewitems
        OverviewGR overviewGR = new OverviewGR();
        overviewGR.setId(1L);
        List<Overviewitem> overviewitems = data.getOverview().getOverviewitems();
        long i=0;
        for(Overviewitem item: overviewitems){
            OverviewitemsGR overviewitemsGR = new OverviewitemsGR();
            overviewitemsGR.setId(i++);
            overviewitemsGR.setTitle(item.getTitle());
            overviewitemsGR.setDescription(item.getDescription());
            overviewitemsGR.setIcon(item.getIcon());
            overviewitemsGR.setOverviewGR(overviewGR);
            overviewitemsGRDao.insert(overviewitemsGR);

        }
        overviewGRDao.insert(overviewGR);

        //insert methodology
        MethodologyGR methodologyGR = new MethodologyGR();
        methodologyGR.setId(1L);

        Methodologycontent methodologycontent = data.getMethodology().getMethodologycontent();

        MethodologyGR_ methodologyGR_ = new MethodologyGR_();
        methodologyGR_.setId(1L);
        methodologyGR_.setBackground(methodologycontent.getBackground());
        methodologyGR_.setLogo(methodologycontent.getLogo());
        methodologyGR_.setMethodologyGR(methodologyGR);
        methodologyGR_dao.insert(methodologyGR_);

        List<Methodologyitem>  methodologyItems = data.getMethodology().getMethodologyitems();
        i=0;
        for(Methodologyitem item: methodologyItems){
            MethodologyItemGR methodologyitemGR = new MethodologyItemGR();
            methodologyitemGR.setId(i++);
            methodologyitemGR.setDetails(item.getDetails());
            methodologyitemGR.setIcon(item.getIcon());
            methodologyitemGR.setTitle(item.getTitle());
            methodologyitemGR.setMethodologyGR_(methodologyGR_);
            methodologyItemGRDao.insert(methodologyitemGR);

        }
        methodologyGRDao.insert(methodologyGR);

        //insert home data
        HomeGR homeGR = new HomeGR();
        homeGR.setId(1L);
        List<Home_> homeItems = data.getHome().getHome();
        i=0;
        for(Home_ item: homeItems){
            HomeGR_ homeGR_ = new HomeGR_();
            homeGR_.setId(i++);
            homeGR_.setTitle(item.getTitle());
            homeGR_.setBackground(item.getBackground());
            homeGR_.setHomeGR(homeGR);
            homeGR_dao.insert(homeGR_);
        }
        homeGRDao.insert(homeGR);
        //insert home portfolio
        PortfolioGR portfolioGR = new PortfolioGR();
        portfolioGR.setId(1L);
        List<Portfolio_>  portfolios = data.getPortfolio().getPortfolio();
        i=0;
        for(Portfolio_ portfolio_: portfolios){
            PortfolioGR_ portfolioGR_ = new PortfolioGR_();
            portfolioGR_.setId(i++);
            portfolioGR_.setTitle(portfolio_.getTitle());
            portfolioGR_.setImage(portfolio_.getImage());
            List<Tag> pTags = portfolio_.getTags();
            long j=0;
            ///tag value not get from AIP
            for(Tag pTag: pTags){
                PTagGR pTagGR = new PTagGR();
                pTagGR.setId((i*10)+(j++));
                pTagGR.setTag(pTag.getTag());
                pTagGR.setUrl(pTag.getUrl());
                pTagGR.setPortfolioGR_(portfolioGR_);
                pTagGRDao.insert(pTagGR);
            }
            portfolioGR_.setPortfolioGR(portfolioGR);
            portfolioGR_dao.insert(portfolioGR_);
        }
        portfolioGRDao.insert(portfolioGR);

        //insert client data
        ClientGR clientGR = new ClientGR();
        clientGR.setId(1L);
        List<Client_>  clients = data.getClient().getClient();
        i=0;
        for(Client_ client_: clients){
            ClientGR_ clientGR_ = new ClientGR_();
            clientGR_.setId(i++);
            clientGR_.setName(client_.getName());
            clientGR_.setCompany(client_.getCompany());
            clientGR_.setCountry(client_.getCountry());
            clientGR_.setLogo(client_.getLogo());
            clientGR_.setClientGR(clientGR);
            List<com.audacityit.audacity.api.response_layer.client.Tag> tags = client_.getTags();
            long j=0;
            ///tag value not get from AIP
            for(com.audacityit.audacity.api.response_layer.client.Tag tag: tags){
                ClientTag clientTag = new ClientTag();
                clientTag.setId((i*10)+(j++));
                clientTag.setTag(tag.getTag());
                clientTag.setUrl(tag.getUrl());
                clientTag.setClientGR_(clientGR_);
                clientTagDao.insert(clientTag);
            }
            clientGR_dao.insert(clientGR_);
        }
        clientGRDao.insert(clientGR);
        //insert team data
        TeamGR teamGR = new TeamGR();
        teamGR.setId(1L);
        List<Team_> team_s = data.getTeam().getTeam();
        i=0;
        for(Team_ item: team_s){
            TeamGR_ teamGR_ = new TeamGR_();
            teamGR_.setId(i++);
            teamGR_.setName(item.getName());
            teamGR_.setDesignation(item.getDesignation());
            teamGR_.setPhoto(item.getPhoto());
            teamGR_.setTeamGR(teamGR);
            teamGR_dao.insert(teamGR_);
        }
        teamGRDao.insert(teamGR);

        //insert social data
        FqaGR fqaGR = new FqaGR();
        fqaGR.setId(1L);
        List<Faq_> faqs = data.getFaq().getFaq();
        i=0;
        for(Faq_ item: faqs){
            FqaGR_ fqaGR_ = new FqaGR_();
            fqaGR_.setId(i++);
            fqaGR_.setQuestion(item.getQuestion());
            fqaGR_.setAnswer(item.getAnswer());
            fqaGR_.setFqaGR(fqaGR);
            fqaGR_dao.insert(fqaGR_);
        }
        fqaGRDao.insert(fqaGR);

        //insert testimonial data
        TestimonialGR testimonialGR = new TestimonialGR();
        testimonialGR.setId(1L);
        List<Testimonial_> testimonials = data.getTestimonial().getTestimonial();
        i=0;

        for(Testimonial_ item: testimonials){
            TestimonialGR_ testimonial_ = new TestimonialGR_();
            testimonial_.setId(i++);
            testimonial_.setName(item.getName());
            testimonial_.setImage(item.getImage());
            testimonial_.setProject(item.getProject());
            testimonial_.setUrl(item.getUrl());
            testimonial_.setFeedback(item.getFeedback());
            testimonial_.setTestimonialGR(testimonialGR);
            testimonialGR_dao.insert(testimonial_);
        }
        testimonialGRDao.insert(testimonialGR);

        //insert getstarted data
        GetstartedGR getstartedGR = new GetstartedGR();
        getstartedGR.setId(1L);
        Getstarted getstarteds = data.getGetstarted();
        getstartedGR.setRange1(getstarteds.getRange1());
        getstartedGR.setRange2(getstarteds.getRange2());
        getstartedGR.setRange3(getstarteds.getRange3());
        getstartedGR.setRange4(getstarteds.getRange4());
        getstartedGR.setPhone(getstarteds.getPhone());
        getstartedGR.setEmail(getstarteds.getEmail());
        getstartedGR.setPhone(getstarteds.getPhone());
        getstartedGR.setWeb(getstarteds.getWeb());
        i=0;

        for(com.audacityit.audacity.api.response_layer.getstarted.Tag item: getstarteds.getTags()){
            GetstartedTag getstartedTag = new GetstartedTag();
            getstartedTag.setId(i++);
            getstartedTag.setTag(item.getTag());
            getstartedTag.setGetstartedGR(getstartedGR);
            getstartedTagDao.insert(getstartedTag);
        }
        getstartedGRDao.insert(getstartedGR);

        //insert social data
        SocialGR socialGR = new SocialGR();
        socialGR.setId(1L);
        List<Social_> socials = data.getSocial().getSocial();
        i=0;
        for(Social_ item: socials){
            SocialGR_ socialGR_ = new SocialGR_();
            socialGR_.setId(i++);
            socialGR_.setImage(item.getImage());
            socialGR_.setName(item.getName());
            socialGR_.setUrl(item.getUrl());
            socialGR_.setSocialGR(socialGR);
            socialGR_dao.insert(socialGR_);
        }
        socialGRDao.insert(socialGR);

        Log.d(mContext+"--insert end-","---");
    }


    //get client from databas
    public List<Client_> getClient(){
        ArrayList<Client_> result = new ArrayList<>();

        ArrayList<ClientGR> clietnGRs = (ArrayList<ClientGR>) clientGRDao.queryBuilder().
                orderAsc(ClientGRDao.Properties.Id).build().list();
        if(clietnGRs.size()>0) {  //if list is not null
            for(ClientGR clientGR : clietnGRs){
                for(ClientGR_ clientGR_ : clientGR.getClientGR_List()){
                    Client_ client_ = new Client_();
                    client_.setName(clientGR_.getName());
                    client_.setCompany(clientGR_.getCompany());
                    client_.setCountry(clientGR_.getCountry());
                    client_.setLogo(clientGR_.getLogo());

                    List<com.audacityit.audacity.api.response_layer.client.Tag> tags = new ArrayList<com.audacityit.audacity.api.response_layer.client.Tag>();
                    for (ClientTag clientTag : clientGR_.getClientTagList()) {
                        com.audacityit.audacity.api.response_layer.client.Tag tag = new com.audacityit.audacity.api.response_layer.client.Tag();
                        tag.setTag(clientTag.getTag());
                        tag.setUrl(clientTag.getUrl());
                        tags.add(tag);
                    }
                    client_.setTags(tags);
                    result.add(client_);
                }
            }
        }
        return result;
    }

    //get portfolio from databas
    public List<Portfolio_> getPortfolio(){
        ArrayList<Portfolio_> result = new ArrayList<>();

        ArrayList<PortfolioGR> portfolioGRs = (ArrayList<PortfolioGR>) portfolioGRDao.queryBuilder().
                orderAsc(PortfolioGRDao.Properties.Id).build().list();
        if(portfolioGRs.size()>0) {  //if list is not null
            for(PortfolioGR portfolioGR : portfolioGRs){
                for(PortfolioGR_ portfolioGR_ : portfolioGR.getPortfolioGR_List()){
                    List<Tag> PTags = new ArrayList<Tag>();
                    for (PTagGR pTagGR : portfolioGR_.getPTagGRList()) {
                        Tag pTag = new Tag();
                        pTag.setTag(pTagGR.getTag());
                        pTag.setUrl(pTagGR.getUrl());
                        PTags.add(pTag);
                    }
                    Portfolio_ portfolio_ = new Portfolio_(portfolioGR_.getTitle(),portfolioGR_.getImage(),PTags);
                    result.add(portfolio_);
                }
            }
        }
        return result;
    }

    //get testimonial value from databse
    public List<Team_> getTeam(){
        ArrayList<Team_> result = new ArrayList<>();

        ArrayList<TeamGR> teamGRs = (ArrayList<TeamGR>) teamGRDao.queryBuilder().
                orderAsc(TeamGRDao.Properties.Id).build().list();
        if(teamGRs.size()>0) {  //if list is not null
            for(TeamGR teamGR : teamGRs){
                for(TeamGR_ teamGR_ :teamGR.getTeamGR_List()){
                    result.add(new Team_(teamGR_.getName(),teamGR_.getDesignation(),teamGR_.getPhoto()));
                }
            }
        }
        return result;
    }

    //get testimonial value from databse
    public List<Testimonial_> getTestimonial(){
        ArrayList<Testimonial_> result = new ArrayList<>();

        ArrayList<TestimonialGR> testimonial = (ArrayList<TestimonialGR>) testimonialGRDao.queryBuilder().
                orderAsc(TestimonialGRDao.Properties.Id).build().list();
        if(testimonial.size()>0) {  //if list is not null
            for(TestimonialGR testimonialGR : testimonial){
                for(TestimonialGR_ testimonialGR_ : testimonialGR.getTestimonialGR_List()){
                    result.add(new Testimonial_(testimonialGR_.getName(),testimonialGR_.getImage(),testimonialGR_.getProject(),testimonialGR_.getUrl(),testimonialGR_.getFeedback()));
                }
            }
        }
        return result;
    }

    //get fqa value from databse
    public List<Faq_> getFaq(){
        ArrayList<Faq_> result = new ArrayList<>();

        ArrayList<FqaGR> fqaGRs = (ArrayList<FqaGR>) fqaGRDao.queryBuilder().
                orderAsc(FqaGRDao.Properties.Id).build().list();
        if(fqaGRs.size()>0) {  //if list is not null

            for(FqaGR fqaGR : fqaGRs){
                for(FqaGR_ fqaGR_ :fqaGR.getFqaGR_List()){
                    result.add(new Faq_(fqaGR_.getQuestion(),fqaGR_.getAnswer()));
                }
            }
        }
        return result;
    }

    //get social value from databse
    public List<Social_> getSocial(){
        ArrayList<Social_> result = new ArrayList<>();

        ArrayList<SocialGR> socialGRs = (ArrayList<SocialGR>) socialGRDao.queryBuilder().
                orderAsc(SocialGRDao.Properties.Id).build().list();
        if(socialGRs.size()>0) {  //if list is not null
            for(SocialGR socialGR : socialGRs){
                for(SocialGR_ socialGR_ :socialGR.getSocialGR_List()){
                    result.add(new Social_(socialGR_.getName(),socialGR_.getImage(),socialGR_.getUrl()));
                }
            }
        }
        return result;
    }

    //get getstarted value from databse
    public Getstarted getStarted(){
        Getstarted result = new Getstarted();

        ArrayList<GetstartedGR> getstartedGRs = (ArrayList<GetstartedGR>) getstartedGRDao.queryBuilder().
                orderAsc(GetstartedGRDao.Properties.Id).build().list();
        if(getstartedGRs.size()>0) {  //if list is not null
            for(GetstartedGR item : getstartedGRs){
                result.setRange1(item.getRange1());
                result.setRange2(item.getRange2());
                result.setRange3(item.getRange3());
                result.setRange4(item.getRange4());

                List<com.audacityit.audacity.api.response_layer.getstarted.Tag> tags = new ArrayList<com.audacityit.audacity.api.response_layer.getstarted.Tag>();
                for(GetstartedTag getstartedTag : item.getGetstartedTagList()){
                    com.audacityit.audacity.api.response_layer.getstarted.Tag tag = new com.audacityit.audacity.api.response_layer.getstarted.Tag();
                    tag.setTag(getstartedTag.getTag());
                    tags.add(tag);
                }
                result.setTags(tags);
            }
        }
        return result;
    }
    //get home value from databse
    public List<Home_> getHome(){
        ArrayList<Home_> result = new ArrayList<>();

        ArrayList<HomeGR> homeGRs = (ArrayList<HomeGR>) homeGRDao.queryBuilder().
                orderAsc(HomeGRDao.Properties.Id).build().list();
        if(homeGRs.size()>0) {  //if list is not null
            for(HomeGR homeGR : homeGRs){
                for(HomeGR_ homeGR_ :homeGR.getHomeGR_List()){
                    result.add(new Home_(homeGR_.getTitle(),homeGR_.getBackground()));
                }
            }
        }
        return result;
    }

    //get overviewcontent value from database
    public Overview getOverview(){
        Overview overview = new Overview();
        Overviewcontent overviewcontentRs = new Overviewcontent();

        ArrayList<OverviewcontentGR> post = (ArrayList<OverviewcontentGR>) overviewcontentGRDao.queryBuilder().
                orderAsc(OverviewcontentGRDao.Properties.Id).build().list();
        if(post.size()>0) {  //if list is not null
            OverviewcontentGR overviewcontent = post.get(0);
            overviewcontentRs.setTitle(overviewcontent.getTitle());
            overviewcontentRs.setLogo(overviewcontent.getLogo());
            overviewcontentRs.setBackground(overviewcontent.getBackground());
            overviewcontentRs.setMoto(overviewcontent.getMoto());
            overviewcontentRs.setCount1text(overviewcontent.getCount1text());
            overviewcontentRs.setCount1no(overviewcontent.getCount1no());
            overviewcontentRs.setCount2text(overviewcontent.getCount2text());
            overviewcontentRs.setCount2no(overviewcontent.getCount2no());
            overviewcontentRs.setCount3text(overviewcontent.getCount3text());
            overviewcontentRs.setCount3no(overviewcontent.getCount3no());
        }

        ArrayList<Overviewitem> overviewitems = new ArrayList<>();
        ArrayList<OverviewGR> overviewGR = (ArrayList<OverviewGR>) overviewGRDao.queryBuilder().
                orderAsc(OverviewGRDao.Properties.Id).build().list();
        if(overviewGR.size()>0) {  //if list is not null
            for(OverviewGR overviewGR1 : overviewGR){
                for(OverviewitemsGR overviewitemsGR : overviewGR1.getOverviewitemsGRList()){
                    overviewitems.add(new Overviewitem(overviewitemsGR.getTitle(),
                            overviewitemsGR.getDescription(), overviewitemsGR.getIcon()));
                }
            }
        }

        overview.setOverviewitems(overviewitems);
        overview.setOverviewcontent(overviewcontentRs);
        return overview;
    }

    //get getMethodology
    public Methodology getMethodology(){
        Methodology methodology = new Methodology();
        Methodologycontent methodologycontent = new Methodologycontent();
        List<Methodologyitem> methodologyitems = new ArrayList<>();

        ArrayList<MethodologyGR> post = (ArrayList<MethodologyGR>) methodologyGRDao.queryBuilder().
                orderAsc(MethodologyGRDao.Properties.Id).build().list();
        List<MethodologyGR_> methodologyGR_ = new ArrayList<>();

        if(post.size()>0) {
            methodologyGR_ = post.get(0).getMethodologyGR_List();

            if (methodologyGR_.size() > 0) {
                methodologycontent.setBackground(methodologyGR_.get(0).getBackground());
                methodologycontent.setLogo(methodologyGR_.get(0).getLogo());

                for(MethodologyItemGR methodologyitemGR  : methodologyGR_.get(0).getMethodologyItemGRList()){
                    methodologyitems.add(new Methodologyitem(methodologyitemGR.getTitle(), methodologyitemGR.getDetails(), methodologyitemGR.getIcon()));
                }
            }
        }

        methodology.setMethodologyitems(methodologyitems);
        methodology.setMethodologycontent(methodologycontent);
        return methodology;
    }

    //get splsh value from database
    public Splash getSplash(){
        Splash result = new Splash();
        ArrayList<SplashGR> post = (ArrayList<SplashGR>) splashGRDao.queryBuilder().
                orderAsc(SplashGRDao.Properties.Id).build().list();
        if(post.size()>0) {  //if list is not null
            result.setBgcolor(post.get(0).getBgcolor());
            result.setLogo(post.get(0).getLogo());
            return result;
        }
        return null;
    }

    //get slide bar value from database
    public Sidebar getSideBar(){
        Sidebar result = new Sidebar();
        ArrayList<SlideBarGR> post = (ArrayList<SlideBarGR>) slideBarGRDao.queryBuilder().
                orderAsc(SlideBarGRDao.Properties.Id).build().list();
        if(post.size()>0) {  //if list is not null
            result.setBackground(post.get(0).getBackground());
            result.setLogo(post.get(0).getLogo());
            result.setPhone(post.get(0).getPhone());
            result.setWeb(post.get(0).getWeb());
            result.setEmail(post.get(0).getEmail());

            return result;
        }
        return null;
    }
}
