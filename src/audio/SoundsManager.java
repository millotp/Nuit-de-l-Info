package audio;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class SoundsManager {

	private Audio backgroundMusic;
	private Audio musicEffect;

	public SoundsManager() {
	}

	public void addMusicEffect(String effect) {
		try {
			if (effect == "inox") {
				musicEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/inox.wav"));
				musicEffect.playAsSoundEffect(1.0f, 1.0f, false);
			}
			if (effect == "explosion") {
				musicEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/explosion.wav"));
				musicEffect.playAsSoundEffect(1.0f, 1.0f, false);
			}
			if (effect == "laser") {
				musicEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/laser.wav"));
				musicEffect.playAsSoundEffect(1.0f, 1.0f, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void changeMusicTheme(String theme) {
		try {
			if (theme == "moon") {
				backgroundMusic = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/espace.wav"));
				backgroundMusic.playAsMusic(1.0f, 1.0f, true);
			}
			if (theme == "xmas") {
				backgroundMusic = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/noel.wav"));
				backgroundMusic.playAsMusic(1.0f, 1.0f, true);
			}
			if (theme == "ring") {
				backgroundMusic = AudioLoader.getAudio("WAV",
						ResourceLoader.getResourceAsStream("sounds/seigneur.wav"));
				backgroundMusic.playAsMusic(1.0f, 1.0f, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

}
