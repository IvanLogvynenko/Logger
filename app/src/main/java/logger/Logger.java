package logger;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Logger extends Thread {
    // * Singleton(((
    private static Logger logger = null;

    private Queue<Log> queue = new LinkedBlockingQueue<>();

    private String path = "";

    private Logger(String path) {
        this.path = path;
    }

    public static Logger getLogger() {
        if (logger == null) {
            logger = new Logger("~");
        }
		logger.start();
        return logger;
    }
	public static Logger getLogger(String path) {
        if (logger == null) {
            logger = new Logger("~." + path);
        }
        return logger;
	}
	
	/**
	 * @param message Any String that you want to be logged
	 */
	public void log(String message) {
		synchronized (queue) {
			queue.add(new Log(message));
		}
		synchronized (this) {
            this.notify();
        }
	}


	private AtomicBoolean shutdown = new AtomicBoolean(false);
	
	@Override
	public void run() {
		while (!shutdown.get()) {
			boolean queueEmpty = false;
			synchronized (queue) {
				queueEmpty = queue.isEmpty();
			}
			if (queueEmpty)
				try {
					synchronized (this) {
						this.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			else {
				Log log;
				synchronized (queue) {
					while (!queue.isEmpty()) {
						log = queue.poll();
						if (log == null)
						    continue;
						System.out.println(log.print(path, null));
					}
				}
			}
		}
	}

	public void flush() {
		shutdown.set(true);
		synchronized (this) {
			this.notify();
        }
	}
	
}
