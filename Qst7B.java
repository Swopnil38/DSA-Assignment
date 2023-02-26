import java.util.*;

public class Qst7B {

    Queue<String> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    int workingThreads = 0;

    public void crawl() {
        OUTER_LOOP: while(true) {
            String nextUrl;
            synchronized(this) {
                while(queue.isEmpty()) {
                    if(workingThreads == 0) {
                        break OUTER_LOOP;
                    }
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                nextUrl = queue.poll();
                workingThreads++;
            }
            List<String> URLs = getLinks(nextUrl);

            synchronized(this) {
                for(String newUrl: URLs) {  // 'URLs' instead of 'urls'
                    if(!visited.contains(newUrl)) {
                        queue.offer(newUrl);
                        visited.add(newUrl);
                    }
                }
                workingThreads--;
                notifyAll();
            }
        }
    }

    // Sample method to get a list of links on a webpage
    public List<String> getLinks(String url) {
        List<String> links = new ArrayList<>();
        // Code to fetch links goes here
        return links;
    }

    public static void main(String[] args) {
        // Create a new instance of Q7_b
        Qst7B webCrawler = new Qst7B();

        // Add a starting URL to the queue
        String startingUrl = "https://http://www.flyfrontier.com";
        webCrawler.queue.offer(startingUrl);
        webCrawler.visited.add(startingUrl);

        // Create an array of worker threads
        Thread[] workers = new Thread[5];
        for (int i = 0; i < workers.length; i++) {
            // Each worker runs the crawl() method of the webCrawler instance
            workers[i] = new Thread(webCrawler::crawl);
            workers[i].start();
        }

        // Wait for all worker threads to finish
        for (Thread worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print the URLs that were visited
        System.out.println("Visited URLs:");
        for (String url : webCrawler.visited) {
            System.out.println(url);
        }
    }

}