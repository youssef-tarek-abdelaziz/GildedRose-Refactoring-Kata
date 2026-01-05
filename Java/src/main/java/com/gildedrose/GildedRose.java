package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    String AGED_BRIE = "Aged Brie";
    String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if(isUnchangeableItem(i))
                continue;
            if (isAgedItem(i) && isLessThanMaxQuality(i)) {
                increaseQuality(i);
                if (items[i].name.equals(BACKSTAGE)) {
                    if (items[i].sellIn < 11) {
                        if (isLessThanMaxQuality(i)) {
                            increaseQuality(i);
                        }
                    }

                    if (items[i].sellIn < 6) {
                        if (isLessThanMaxQuality(i)) {
                            increaseQuality(i);
                        }
                    }
                }
            } else {
                if (isQualityGreaterThanZero(i)) {
                    decreaseQuality(i);
                }
            }

            decreaseSellIn(i);

            if (items[i].sellIn < 0) {
                if (items[i].name.equals(AGED_BRIE)) {
                    if (isLessThanMaxQuality(i)) {
                        increaseQuality(i);
                    }
                } else {
                    if (items[i].name.equals(BACKSTAGE)) {
                        items[i].quality = 0;
                    } else {
                        if (isQualityGreaterThanZero(i)) {
                            decreaseQuality(i);
                        }
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
