
package com.audacityit.audacity.api.response_layer.all;


import com.audacityit.audacity.api.response_layer.client.Client;
import com.audacityit.audacity.api.response_layer.faq.Faq;
import com.audacityit.audacity.api.response_layer.getstarted.Getstarted;
import com.audacityit.audacity.api.response_layer.home.Home;
import com.audacityit.audacity.api.response_layer.methodology.Methodology;
import com.audacityit.audacity.api.response_layer.overview.Overview;
import com.audacityit.audacity.api.response_layer.portfolio.Portfolio;
import com.audacityit.audacity.api.response_layer.sidebar.Sidebar;
import com.audacityit.audacity.api.response_layer.social.Social;
import com.audacityit.audacity.api.response_layer.splash.Splash;
import com.audacityit.audacity.api.response_layer.team.Team;
import com.audacityit.audacity.api.response_layer.testimonial.Testimonial;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Data {

    @SerializedName("sidebar")
    @Expose
    private Sidebar sidebar;
    @SerializedName("splash")
    @Expose
    private Splash splash;
    @SerializedName("overview")
    @Expose
    private Overview overview;
    @SerializedName("home")
    @Expose
    private Home home;
    @SerializedName("portfolio")
    @Expose
    private Portfolio portfolio;
    @SerializedName("team")
    @Expose
    private Team team;
    @SerializedName("client")
    @Expose
    private Client client;
    @SerializedName("testimonial")
    @Expose
    private Testimonial testimonial;
    @SerializedName("methodology")
    @Expose
    private Methodology methodology;
    @SerializedName("faq")
    @Expose
    private Faq faq;
    @SerializedName("getstarted")
    @Expose
    private Getstarted getstarted;
    @SerializedName("social")
    @Expose
    private Social social;
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     *
     * @return
     *     The sidebar
     */
    public Sidebar getSidebar() {
        return sidebar;
    }

    /**
     *
     * @param sidebar
     *     The sidebar
     */
    public void setSidebar(Sidebar sidebar) {
        this.sidebar = sidebar;
    }

    /**
     *
     * @return
     *     The splash
     */
    public Splash getSplash() {
        return splash;
    }

    /**
     *
     * @param splash
     *     The splash
     */
    public void setSplash(Splash splash) {
        this.splash = splash;
    }

    /**
     *
     * @return
     *     The overview
     */
    public Overview getOverview() {
        return overview;
    }

    /**
     *
     * @param overview
     *     The overview
     */
    public void setOverview(Overview overview) {
        this.overview = overview;
    }

    /**
     *
     * @return
     *     The home
     */
    public Home getHome() {
        return home;
    }

    /**
     *
     * @param home
     *     The home
     */
    public void setHome(Home home) {
        this.home = home;
    }

    /**
     *
     * @return
     *     The portfolio
     */
    public Portfolio getPortfolio() {
        return portfolio;
    }

    /**
     *
     * @param portfolio
     *     The portfolio
     */
    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    /**
     *
     * @return
     *     The team
     */
    public Team getTeam() {
        return team;
    }

    /**
     *
     * @param team
     *     The team
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     *
     * @return
     *     The client
     */
    public Client getClient() {
        return client;
    }

    /**
     *
     * @param client
     *     The client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     *
     * @return
     *     The testimonial
     */
    public Testimonial getTestimonial() {
        return testimonial;
    }

    /**
     *
     * @param testimonial
     *     The testimonial
     */
    public void setTestimonial(Testimonial testimonial) {
        this.testimonial = testimonial;
    }

    /**
     *
     * @return
     *     The methodology
     */
    public Methodology getMethodology() {
        return methodology;
    }

    /**
     *
     * @param methodology
     *     The methodology
     */
    public void setMethodology(Methodology methodology) {
        this.methodology = methodology;
    }

    /**
     *
     * @return
     *     The faq
     */
    public Faq getFaq() {
        return faq;
    }

    /**
     *
     * @param faq
     *     The faq
     */
    public void setFaq(Faq faq) {
        this.faq = faq;
    }

    /**
     *
     * @return
     *     The getstarted
     */
    public Getstarted getGetstarted() {
        return getstarted;
    }

    /**
     *
     * @param getstarted
     *     The getstarted
     */
    public void setGetstarted(Getstarted getstarted) {
        this.getstarted = getstarted;
    }

    /**
     *
     * @return
     *     The social
     */
    public Social getSocial() {
        return social;
    }

    /**
     *
     * @param social
     *     The social
     */
    public void setSocial(Social social) {
        this.social = social;
    }

    /**
     *
     * @return
     *     The error
     */
    public boolean isError() {
        return error;
    }

    /**
     *
     * @param error
     *     The error
     */
    public void setError(boolean error) {
        this.error = error;
    }

    /**
     *
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
