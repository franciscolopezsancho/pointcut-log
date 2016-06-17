package org.aop.logger.decorator;

import java.io.File;

import rx.fileutils.FileSystemEvent;

public class FileDecorator extends Decorator {

	public FileDecorator(Printer p) {
		super(p);
	}

	@Override
	public String print(Object object) {
		StringBuilder result = new StringBuilder(super.print(object));
		if (object instanceof File)
			result.append(((File) object).getAbsolutePath());
		else if (object instanceof FileSystemEvent)
			result.append("event kind: ").append(((FileSystemEvent) object).getFileSystemEventKind())
					.append(" , path: ").append(((FileSystemEvent) object).getPath());
		return result.toString();
	}

}
