package types;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class InventoryClickListSorter {
    public static InventoryClickList sort(InventoryClickList agg, InventoryClick current, int limit) {
        var all = new ArrayList<>(agg.getItems());
        all.add(current);

        var sorted = all.stream().sorted(InventoryClickComparator.comparator);
        var topN = sorted.limit(limit).collect(Collectors.toList());

        return new InventoryClickList(topN);
    }

    private static class InventoryClickComparator {
        public static Comparator<InventoryClick> comparator;

        static {
            comparator = get();
        }

        private static Comparator<InventoryClick> get() {
            Comparator<InventoryClick> clickCount = Comparator
                    .comparing(InventoryClick::getClickCount);
            Comparator<InventoryClick> clickCountDesc = clickCount.reversed();

            Comparator<InventoryClick> name = Comparator.comparing(inv -> inv.getInventoryID().toString());

            return clickCountDesc.thenComparing(name);
        }
    }
}
