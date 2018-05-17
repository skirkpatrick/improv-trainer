package com.github.improvtrainer.model;

public enum ChordQuality {

    MAJ(new int[]{4, 7}, new int[]{}, ""),
    MAJ6(new int[]{4, 7, 9}, new int[]{}, "6"),
    MAJ7(new int[]{4, 7, 11}, new int[]{}, "Maj7"),
    MAJ7_FLAT3_FLAT5(new int[]{3, 6, 11}, new int[]{}, ""),
    MAJ7_FLAT3_SHARP5(new int[]{3, 8, 11}, new int[]{}, ""),
    MAJ7_SHARP5(new int[]{4, 8, 11}, new int[]{}, ""),
    MAJ7_FLAT9(new int[]{4, 7, 11, 1}, new int[]{}, ""),
    MAJ7_SHARP9(new int[]{4, 7, 11, 3}, new int[]{}, ""),
    MAJ7_SHARP11(new int[]{4, 7, 11, 6}, new int[]{}, ""),
    MAJ9(new int[]{4, 7, 2}, new int[]{}, ""),
    MAJ9_SHARP5(new int[]{4, 8, 2}, new int[]{}, ""),
    MAJ9_SHARP11(new int[]{4, 7, 11, 2, 6}, new int[]{}, ""),

    MIN(new int[]{3, 7}, new int[]{}, "m"),
    MIN6(new int[]{3, 7, 9}, new int[]{}, ""),
    MIN7(new int[]{3, 7, 10}, new int[]{}, "m7"),
    MIN7_FLAT5(new int[]{3, 6, 10}, new int[]{}, ""),
    MIN7_SHARP5(new int[]{3, 8, 10}, new int[]{}, ""),
    MIN7_FLAT9(new int[]{3, 7, 10, 1}, new int[]{}, ""),
    MIN9(new int[]{3, 7, 1}, new int[]{}, ""),
    MIN9_FLAT5(new int[]{3, 6, 1}, new int[]{}, ""),
    MIN9_SHARP5(new int[]{3, 8, 1}, new int[]{}, ""),
    MIN9_NAT7(new int[]{}, new int[]{}, ""),

    MAJ_MIN7(new int[]{}, new int[]{}, ""),
    MAJ_MIN9(new int[]{}, new int[]{}, ""),
    
    SIX_NINE(new int[]{4, 7, 9, 2}, new int[]{}, "69"),
    DOM7(new int[]{4, 7, 10}, new int[]{}, "7"),
    SEVEN_SUS4(new int[]{5, 7, 10}, new int[]{}, ""),
    SEVEN_FLAT5(new int[]{4, 6, 10}, new int[]{}, ""),
    SEVEN_SHARP5(new int[]{4, 8, 10}, new int[]{}, "7#5"),
    SEVEN_SHARP5_FLAT9(new int[]{}, new int[]{}, ""),
    SEVEN_FLAT9(new int[]{4, 7, 10, 1}, new int[]{}, ""),
    SEVEN_SHARP9(new int[]{4, 7, 10, 3}, new int[]{}, ""),
    SEVEN_ADD13(new int[]{}, new int[]{}, ""),
    NINE(new int[]{4, 7, 10, 2}, new int[]{}, ""),
    NINE_FLAT5(new int[]{4, 6, 10, 2}, new int[]{}, ""),
    NINE_SHARP5(new int[]{4, 8, 10, 2}, new int[]{}, ""),
    ELEVEN(new int[]{4, 7, 10, 2, 5}, new int[]{}, ""),
    ELEVEN_SHARP5(new int[]{4, 8, 10, 2, 5}, new int[]{}, ""),
    ELEVEN_FLAT9(new int[]{4, 7, 10, 1, 5}, new int[]{}, ""),

    AUG(new int[]{4, 8}, new int[]{}, ""),
    DIM(new int[]{3, 6}, new int[]{}, "dim");


    private final int[] strongToneOffsets;
    private final int[] weakToneOffsets;
    private final String displayName;

    ChordQuality(int[] strong, int[] weak, String displayName) {
        this.strongToneOffsets = strong;
        this.weakToneOffsets = weak;
        this.displayName = displayName;
    }

    public int[] getStrongToneOffsets() {
        return strongToneOffsets;
    }

    public int[] getWeakToneOffsets() {
        return weakToneOffsets;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ChordQuality fromString(String s) {
        if (s == null) return MAJ;

        switch (s) {
            case "7":
                return DOM7;
            case "m7":
                return MIN7;
            case "maj7":
            case "Maj7":
                return MAJ7;
            case "m":
                return MIN;
            case "dim":
                return DIM;
            case "69":
                return SIX_NINE;
            case "7#5":
                return SEVEN_SHARP5;
            default:
                return MAJ;
        }
    }

    private static int[] arr(int... vals) {
        return vals;
    }
}
