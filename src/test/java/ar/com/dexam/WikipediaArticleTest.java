package ar.com.dexam;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


class WikipediaArticleTest {

    @Test
    void getTopicCountWhenWhenTheWordIsLowCaseAndHasACapitalLetter() {
        Assert.assertEquals(84, WikipediaArticle.getTopicCount("Pizza"));
    }

    @Test
    void getTopicCountWhenTheWordIsLowCase() {
        Assert.assertEquals(85, WikipediaArticle.getTopicCount("pizza"));
    }

    @Test
    void getTopicCountWhenThePizzaIsUpperCase() {
        Assert.assertEquals(0, WikipediaArticle.getTopicCount("PIZZA"));
    }

    @Test
    void getTopicCountWhenTheWordIsSparrow() {
        Assert.assertEquals(9, WikipediaArticle.getTopicCount("sparrow"));
    }

    @Test
    void getTopicCountWhenTheWordIsFrance() {
        Assert.assertEquals(0, WikipediaArticle.getTopicCount("france"));
    }



}