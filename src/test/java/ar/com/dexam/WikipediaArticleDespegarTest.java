package ar.com.dexam;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


class WikipediaArticleDespegarTest {

    @Test
    void getTopicCountWhenWhenTheWordIsLowCaseAndHasACapitalLetterShouldBe121() {
        Assert.assertEquals(121, WikipediaArticleDespegar.getTopicCount("Pizza"));
    }

    @Test
    void getTopicCountWhenTheWordIsLowCaseShouldBe139() {
        Assert.assertEquals(139, WikipediaArticleDespegar.getTopicCount("pizza"));
    }

    @Test
    void getTopicCountWhenTheWordIsUpperCaseShouldBeZero() {
        Assert.assertEquals(0, WikipediaArticleDespegar.getTopicCount("PIZZA"));
    }
}