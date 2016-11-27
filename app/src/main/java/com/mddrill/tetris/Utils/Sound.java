package com.mddrill.tetris.Utils;

/**
 * Created by mddri_000 on 11/22/2016.
 */

public enum Sound {
    clearRowSound(Applet.newAudioClip(Sound.class.getResource("Clear_Row.wav"))),
    errorSound(Applet.newAudioClip(Sound.class.getResource("Error.wav"))),
    gameSong(Applet.newAudioClip(Sound.class.getResource("Game_Song.wav")));

    private final AudioClip audioclip;

    private Sound(AudioClip audioclip) {
        this.audioclip = audioclip;
    }

    public static void playClearRowSound() {
        clearRowSound.audioclip.play();
    }

    public static void playErrorSound() {
        errorSound.audioclip.play();
    }
    public static void playGameSong() {
        gameSong.audioclip.play();
    }

    public static Sound getClearRowSound() {
        return clearRowSound;
    }

    public static Sound getErrorSound() {
        return errorSound;
    }

    public static Sound getGameSong() {
        return gameSong;
    }
}
