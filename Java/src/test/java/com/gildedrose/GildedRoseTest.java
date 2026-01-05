package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private final String AGED_BRIE = "Aged Brie";
    private final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }
    @Test
    void foo_qualityDecreasedByTwo() {
        Item[] items = new Item[] { new Item("foo", 1, 10) };
        GildedRose app = new GildedRose(items);
        int days = 5;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertEquals("foo", app.items[0].name);
        assertEquals(1, app.items[0].quality);
        assertEquals(-4, app.items[0].sellIn);
    }

    @Test
    void foo_noNegativeQuality() {
        Item[] items = new Item[] { new Item("foo", 0, 10) };
        GildedRose app = new GildedRose(items);
        int days = 5;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertEquals("foo", app.items[0].name);
        assertEquals(0, app.items[0].quality);
        assertEquals(-5, app.items[0].sellIn);
    }

    @Test
    void agedBrie() {
        Item[] items = new Item[] { new Item(AGED_BRIE, 5, 5) };
        GildedRose app = new GildedRose(items);
        int days = 5;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertEquals(AGED_BRIE, app.items[0].name);
        assertEquals(10, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    void agedBrie_qualityNeverMoreThan50() {
        Item[] items = new Item[] { new Item(AGED_BRIE, 5, 50) };
        GildedRose app = new GildedRose(items);
        int days = 5;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertEquals(AGED_BRIE, app.items[0].name);
        assertEquals(50, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    void agedBrie_negativeSellIn_QualityIncreasesTheDouble() {
        Item[] items = new Item[] { new Item(AGED_BRIE, 5, 10) };
        GildedRose app = new GildedRose(items);
        int days = 10;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertEquals(AGED_BRIE, app.items[0].name);
        assertEquals(25, app.items[0].quality);
        assertEquals(-5, app.items[0].sellIn);
    }

    @Test
    void sulfuras_qualityAndSellInNeverDecreased() {
        Item[] items = new Item[] { new Item(SULFURAS, 5, 80) };
        GildedRose app = new GildedRose(items);
        int days = 10;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertEquals(SULFURAS, app.items[0].name);
        assertEquals(80, app.items[0].quality);
        assertEquals(5, app.items[0].sellIn);
    }

    @Test
    void sulfuras_negativeSellIn() {
        Item[] items = new Item[] { new Item(SULFURAS, -1, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(SULFURAS, app.items[0].name);
        assertEquals(10, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void agedBrie_negativeSellIn() {
        Item[] items = new Item[] { new Item(AGED_BRIE, -1, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(AGED_BRIE, app.items[0].name);
        assertEquals(50, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    void backstage_increaseQualityByOne() {
        Item[] items = new Item[] { new Item(BACKSTAGE, 15, 10) };
        GildedRose app = new GildedRose(items);
        int days = 5;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertEquals(BACKSTAGE, app.items[0].name);
        assertEquals(15, app.items[0].quality);
        assertEquals(10, app.items[0].sellIn);
    }

    @Test
    void backstage_increaseQualityByTwo() {
        Item[] items = new Item[] { new Item(BACKSTAGE, 10, 10) };
        GildedRose app = new GildedRose(items);
        int days = 5;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertEquals(BACKSTAGE, app.items[0].name);
        assertEquals(20, app.items[0].quality);
        assertEquals(5, app.items[0].sellIn);
    }
    @Test
    void backstage_increaseQualityByThree() {
        Item[] items = new Item[] { new Item(BACKSTAGE, 5, 10) };
        GildedRose app = new GildedRose(items);
        int days = 2;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertEquals(BACKSTAGE, app.items[0].name);
        assertEquals(16, app.items[0].quality);
        assertEquals(3, app.items[0].sellIn);
    }
    @Test
    void backstage_increaseQualityWithAllLevel() {
        Item[] items = new Item[] { new Item(BACKSTAGE, 15, 10) };
        GildedRose app = new GildedRose(items);
        int days = 15;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertEquals(BACKSTAGE, app.items[0].name);
        assertEquals(40, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    void backstage_qualityBecomesFiftySellIn10() {
        Item[] items = new Item[] { new Item(BACKSTAGE, 10, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(BACKSTAGE, app.items[0].name);
        assertEquals(50, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    void backstage_qualityBecomesFiftySellIn5() {
        Item[] items = new Item[] { new Item(BACKSTAGE, 5, 48) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(BACKSTAGE, app.items[0].name);
        assertEquals(50, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
    }

    @Test
    void backstage_resetQuality() {
        Item[] items = new Item[] { new Item(BACKSTAGE, 15, 10) };
        GildedRose app = new GildedRose(items);
        int days = 16;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertEquals(BACKSTAGE, app.items[0].name);
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }


}
