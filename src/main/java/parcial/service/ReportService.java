package parcial.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parcial.model.Cryptocoin;
import parcial.model.User;
import parcial.model.UserReport;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ReportService {

    private static final Logger logger = LogManager.getLogger(ReportService.class);

    public static void generateFinalReport(List<User> users, List<Cryptocoin> cryptocoins) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Queue<UserReport> reports = new LinkedList<>();

        for (User user : users) {

                double portfolioValueUSD = 0.0;
                for (Cryptocoin c : user.getWallet().getItems()) {
                    double priceUSD = cryptocoins.stream()
                            .filter(cc -> cc.getName().equals(c.getName()))
                            .mapToDouble(cc -> Double.parseDouble(cc.getPrice_usd()))
                            .findFirst()
                            .orElse(0.0);
                    portfolioValueUSD += priceUSD;
                }

            reports.add(new UserReport(
                    user.getId().toString(),
                    user.getName(),
                    user.getBalance(),
                    user.getBalance() / 4000.0,
                    portfolioValueUSD * 4000.0,
                    user.getWallet(),
                    user.getTransactionHistory()
            ));

        }

        try (FileWriter writer = new FileWriter("final_report.json")) {
            gson.toJson(reports, writer);
            logger.info("Final report generated successfully: final_report.json");
        } catch (IOException e) {
            logger.error("Error Writing: {}", e.getMessage());
        }

    }
}
