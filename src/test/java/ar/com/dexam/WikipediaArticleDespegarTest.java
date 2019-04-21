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

    @Test
    void getTopicCountWhenTheWordIssparrowShouldBe17() {
        Assert.assertEquals(17, WikipediaArticleDespegar.getTopicCount("sparrow"));
    }

    @Test
    void getTopicCountWhenTheWordIsfranceShouldBeZero3() {
        Assert.assertEquals(3, WikipediaArticleDespegar.getTopicCount("france"));
    }

}