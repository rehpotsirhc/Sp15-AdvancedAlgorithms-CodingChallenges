import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Chris on 2/7/2015.
 */
class MaxComp {


    private static class Event implements Comparable {
        int s;
        int e;
        int c;
        boolean used;

        public Event(int s, int e, int c) {
            this.s = s;
            this.e = e;
            this.c = c;
        }

        @Override
        public int compareTo(Object o) {
            Event that = (Event) o;
            return that.c - this.c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());


        while (T-- > 0) {

            int N = Integer.parseInt(br.readLine());
            Event[] events = new Event[N];
            List<ArrayList<Event>> eventsByHour = new ArrayList<ArrayList<Event>>();
            Event[] schedule = new Event[49];

            for (int i = 0; i <= 48; i++) {
                eventsByHour.add(i, new ArrayList<Event>());
            }

            for (int i = 0; i < N; i++) {

                String[] line = br.readLine().split(" ");
                int s = Integer.parseInt(line[0]);
                int e = Integer.parseInt(line[1]);
                events[i] = new Event(s, e, Integer.parseInt(line[2]));
                for (int j = s; j < e; j++) {
                    eventsByHour.get(j).add(events[i]);
                }

            }
            for (ArrayList<Event> hourEvents : eventsByHour) {
                Collections.sort(hourEvents);
            }

            for (int p = 0; p < N; p++) {
                for (int h = 0; h <= 48; h++) {
                    List<Event> eventsAtHour = eventsByHour.get(h);
                    if (!eventsAtHour.isEmpty() && eventsAtHour.size() - 1 >= p) {
                        Event atP = eventsAtHour.get(p);
                        if (!atP.used) {
                            int s = atP.s;
                            int e = atP.e;
                            boolean isBetter = true;
                            for (int i = s; i < e; i++) {
                                if (schedule[i] != null && atP.c < schedule[i].c) {
                                    isBetter = false;
                                    break;
                                }
                            }
                            if (isBetter) {
                                for (int i = s; i < e; i++) {
                                    Event replaced = schedule[i];
                                    if (replaced != null) {
                                        replaced.used = false;
                                        int sr = replaced.s;
                                        int er = replaced.e;
                                        for (int j = sr; j < er; j++) {

                                            schedule[j] = null;
                                        }
                                    }
                                    schedule[i] = atP;
                                    atP.used = true;
                                }
                            }
                        }

                    }
                }


            }

            long sum = 0;

            for (Event ev : events) {
                if (ev.used) sum += ev.c;

            }
            System.out.println(sum);
        }
    }


}




