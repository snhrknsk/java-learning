package ch03.ex22;

public class CompletableFutureFlatmap {

	/*
	 * CompletableFuture<T>のflatMap操作
	 * CompletableFuture<U> thenCompose(Function<? super T,? extends CompletionStage<U>> fn)
	 * CompletableFutureのthenApplyが行われた後thenComposeメソッドでさらに処理が可能
	 *
	 *  // UserIDからUserInfo型のCompletableFutureを返すgetUserInfo関数
 	 *	public CompletableFuture<UserInfo> getUserInfo(int id)
	 *	// UserInfo型のCompletableFutureからUserRating型のCompletableFutureを返すgetUserRating関数が存在
	 *	public CompletableFuture<UserRating> getUserRating(UserInfo)
	 *	// CompletableFutureをFlatMap化して結果を返すことができる?
	 *	CompletableFuture<UserRating> result = getUserInfo(id).thenCompose(this::getUserRating);
	 */
	public static void main(String[] args) {
	}
}
