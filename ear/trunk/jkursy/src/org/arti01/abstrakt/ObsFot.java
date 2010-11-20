package org.arti01.abstrakt;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import org.apache.log4j.Logger;
import org.arti01.sesBean.Fota;

public class ObsFot {
	Logger logger = Logger.getLogger(ObsFot.class);
	Fota fota=new Fota();
	String error;
	
	public boolean obsFoty(File upload) throws Exception {
		upload.exists();
		try {
			upload.exists();
			new ResizeJpg().zrob(800, 600, upload.getAbsolutePath(), upload
					.getAbsolutePath()
					+ "tmp");
			File plik = new File(upload.getAbsolutePath() + "tmp");
			FileInputStream sourceFile = new FileInputStream(plik);
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			byte buffer[] = new byte[4096];
			int bytesRead = 0;
			while ((bytesRead = sourceFile.read(buffer)) != -1)
				arrayOutputStream.write(buffer, 0, bytesRead);
			arrayOutputStream.close();
			fota.setPlik(arrayOutputStream.toByteArray());
			plik.delete();

			// obsluga miniatury
			upload.exists();
			new ResizeJpg().zrob(200, 150, upload.getAbsolutePath(), upload
					.getAbsolutePath()
					+ "tmpMini");
			File plikMini = new File(upload.getAbsolutePath() + "tmpMini");
			FileInputStream sourceFileMini = new FileInputStream(plikMini);
			ByteArrayOutputStream arrayOutputStreamMini = new ByteArrayOutputStream();
			byte bufferMini[] = new byte[4096];
			int bytesReadMini = 0;
			while ((bytesReadMini = sourceFileMini.read(bufferMini)) != -1)
				arrayOutputStreamMini.write(bufferMini, 0, bytesReadMini);
			arrayOutputStreamMini.close();
			fota.setPlikMini(arrayOutputStreamMini.toByteArray());
			logger.info(fota.getPlikMini().length);
			plikMini.delete();
			return true;
		} catch (NullPointerException e) {
			logger.info(e);
			error="pewnie nie dodałeś pliku, co?";
			return false;
		}
	}

	public Fota getFota() {
		return fota;
	}

	public void setFota(Fota fota) {
		this.fota = fota;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
