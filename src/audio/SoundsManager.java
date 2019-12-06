package audio;

import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class SoundsManager {

	private Audio backgroundMusic;
	private Audio musicEffect;

	private HashMap<String, Audio> audios;

	public SoundsManager() {
		audios = new HashMap<String, Audio>();
		try {
			audios.put("inox", AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/inox.wav")));
			audios.put("explosion",
					AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/explosion.wav")));
			audios.put("laser", AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/laser.wav")));
			audios.put("moon", AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/espace.wav")));
			audios.put("xmas", AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/noel.wav")));
			audios.put("ring", AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/seigneur.wav")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addMusicEffect(String effect) {
		if(musicEffect != null) {
			musicEffect.stop();
		}
		musicEffect = audios.get(effect);
		musicEffect.playAsSoundEffect(1.0f, 1.0f, false);
	}

	public void changeMusicTheme(String theme) {
		if(backgroundMusic != null)
			backgroundMusic.stop();
		backgroundMusic = audios.get(theme);
		backgroundMusic.playAsMusic(1, 1, true);
	}

}
