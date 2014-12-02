package resource.moviesuggest;

public class Api {

	public static String movieSearchapi = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=[api_key]&q=[query]&page_limit=[pagelimit]";
	public static String movieInfoapi = "http://api.rottentomatoes.com/api/public/v1.0/movies/[id].json?apikey=[api_key]";
	public static String movieSimilarapi="http://api.rottentomatoes.com/api/public/v1.0/movies/[id]/similar.json?apikey=[api_key]&limit=[pagelimit]";
	public static String key="63ke3tj95cqfq7sk3n2zgfeg";
}
