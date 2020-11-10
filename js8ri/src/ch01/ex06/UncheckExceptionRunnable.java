package ch01.ex06;

import java.util.concurrent.Callable;

public class UncheckExceptionRunnable {

	public static void main(String[] args) {

		//RunnableEx
		new Thread(unckeck(()->{
			System.out.println("Zzz");
			Thread.sleep(1000);
		})).start();

		//Callableでは戻り値が必要なので例題のラムダ式では戻り値が無いためCallableは不可
		//戻り値を追加すればCallableでも可能
		new Thread(unckeck(()->{
			System.out.println("Zzz");
			Thread.sleep(1000);
			return ;
		})).start();

	}

	public static Runnable unckeck( Callable<Void> r) {
		return () -> {
			try {
				r.call();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	}

	public static Runnable unckeck( RunnableEx r) {
		return () -> {
			try {
				r.run();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	}

	interface RunnableEx {
		void run() throws Exception;
	}
}
