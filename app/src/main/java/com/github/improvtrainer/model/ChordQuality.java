package com.github.improvtrainer.model;

public enum ChordQuality {
    DOM7(new int[]{4, 7, 10}, new int[]{}),
    MIN7(new int[]{3, 7, 10}, new int[]{}),
    MAJ(new int[]{4, 7}, new int[]{}),
    MAJ7(new int[]{4, 7, 11}, new int[]{}),
    MAJ6(new int[]{4, 7, 9}, new int[]{}),
    AUG(new int[]{4, 8}, new int[]{}),
    DIM,
    MIN6,
    SEVEN_SUS4,
    SEVEN_SHARP5,
    SEVEN_FLAT5,
    MIN7_FLAT5,
    MIN7_SHARP5,
    MAJ_MIN7,
    MAJ7_SHARP5,
    MAJ7_SHARP11,
    MAJ7_FLAT3_FLAT5,
    MAJ7_FLAT3_SHARP5,
    SEVEN_ADD13,
    NINE,
    SIX_NINE,
    MAJ9,
    MIN9,
    NINE_SHARP5,
    NINE_FLAT5,
    SEVEN_SHARP9,
    SEVEN_FLAT9,
    MAJ7_FLAT9,
    MIN7_FLAT9,
    SEVEN_SHARP5_FLAT9,
    MAJ7_SHARP9,
    MIN9_NAT7,
    MAJ_MIN9,
    MAJ9_SHARP5,
    MAJ9_SHARP11,
    MIN9_FLAT5,
    MIN9_SHARP5,
    ELEVEN,
    ELEVEN_SHARP5,
    ELEVEN_FLAT9,
    MIN;

    private final int[] strongToneOffsets;
    private final int[] weakToneOffsets;

    ChordQuality() {
        this(new int[]{}, new int[]{});
    }

    ChordQuality(int[] strong, int[] weak) {
        this.strongToneOffsets = strong;
        this.weakToneOffsets = weak;
    }

    public int[] getStrongToneOffsets() {
        return strongToneOffsets;
    }

    public int[] getWeakToneOffsets() {
        return weakToneOffsets;
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
            default:
                return MAJ;
        }
    }

    private static int[] arr(int... vals) {
        return vals;
    }
}
