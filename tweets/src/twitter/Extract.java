/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.hamcrest.Matcher;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
    	
    	Timespan t = new Timespan(Instant.now(),Instant.now());
    	if(tweets.isEmpty())
    	{
    		System.out.println("empty list");
    		return t;
    	}
    	 Instant start = tweets.get(0).getTimestamp();
         Instant end = tweets.get(tweets.size()-1).getTimestamp();
         t= new Timespan(start,end);
         return t;
        
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        Pattern myPattern = Pattern.compile("@(\\w+|\\W+)");
        Set<String> mentionedusersInTweet = new HashSet<String>();
        for (Tweet tweet : tweets) {
            String tweetText = tweet.getText();
            java.util.regex.Matcher matcher = myPattern.matcher(tweetText.toLowerCase());
            List<String> mentionedusersLowerCase = new ArrayList<String>();
            //System.out.println("Mentioned names are:");
            while(matcher.find()){
                System.out.println(matcher.group(1));
                
                mentionedusersLowerCase.add(matcher.group(1)); //it will add all the names to list array list in lowercase
                }
            mentionedusersInTweet.addAll(mentionedusersLowerCase); //it will add all the name to mentionedUserinList Set
            }
        return mentionedusersInTweet; 
    	 //i got idea of pattern and matcher classes from stackoverflow and github
    	
    	
         
         
    	
    }

}
