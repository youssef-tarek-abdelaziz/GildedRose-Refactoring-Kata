package com.gildedrose;

import java.util.List;
import java.util.Map;

class Rule {
    int maxSellIn;
    int qualityDelta;

    public Rule(int maxSellIn, int qualityDelta) {
        this.maxSellIn = maxSellIn;
        this.qualityDelta = qualityDelta;
    }
}

class GildedRose {
    Item[] items;
    private final String AGED_BRIE = "Aged Brie";
    private final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    private final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private Map<String, List<Rule>> specialIncreaseRules;

    public GildedRose(Item[] items) {
        this.items = items;
    }



    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if(isUnchangeableItem(i))
                continue;
            if (isAgedItem(i)) {
                increaseAgedItemQuantity(i);
            } else {
                if (isQualityGreaterThanZero(i)) {
                    decreaseQuality(i);
                }
            }

            decreaseSellIn(i);

            if (isNegativeSellIn(i)) {
                updateQuantityBasedOnNegativeSellIn(i);
            }
        }
    }

    private void updateQuantityBasedOnNegativeSellIn(int idx) {
        items[idx].quality = expiredQualityCalculator(idx);
    }

    private int expiredQualityCalculator(int idx) {
        Item item = items[idx];
        if(item.name.equals(AGED_BRIE)) {
            return isLessThanMaxQuality(idx) ? item.quality + 1 : item.quality;
        }
        else if(item.name.equals(BACKSTAGE)) {
            return 0;
        }
        else {
            return isQualityGreaterThanZero(idx) ? item.quality - 1 : 0;
        }
    }

    private boolean isNegativeSellIn(int idx) {
        return items[idx].sellIn < 0;
    }

    private void increaseAgedItemQuantity(int idx) {
        if (isLessThanMaxQuality(idx)) {
            increaseQuality(idx);

            if (items[idx].name.equals(BACKSTAGE)) {
                if (items[idx].sellIn < 11) {
                    if (isLessThanMaxQuality(idx)) {
                        increaseQuality(idx);
                    }
                }

                if (items[idx].sellIn < 6) {
                    if (isLessThanMaxQuality(idx)) {
                        increaseQuality(idx);
                    }
                }
            }
        }
    }

    private boolean isAgedItem(int i) {
        return items[i].name.equals(AGED_BRIE) || items[i].name.equals(BACKSTAGE);
    }

    private void decreaseSellIn(int i) {
        items[i].sellIn = items[i].sellIn - 1;
    }

    private boolean isUnchangeableItem(int idx) {
        return items[idx].name.equals(SULFURAS);
    }

    private boolean isQualityGreaterThanZero(int idx) {
        return items[idx].quality > 0;
    }

    private boolean isLessThanMaxQuality(int i) {
        return items[i].quality < 50;
    }

    private void increaseQuality(int idx) {
        items[idx].quality = items[idx].quality + 1;
    }

    private void decreaseQuality(int idx) {
        items[idx].quality = items[idx].quality - 1;
    }
}
