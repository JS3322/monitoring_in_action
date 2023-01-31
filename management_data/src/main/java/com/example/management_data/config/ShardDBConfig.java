//package com.example.management_data.config;
//
////import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
////import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
////import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
////import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
////import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
////import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
////import com.dangdang.ddframe.rdb.sharding.jdbc.core.datasource.ShardingDataSource;
//import com.zaxxer.hikari.HikariDataSource;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
//import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
//import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
//import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
//import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Map;
//import java.util.Properties;
//import java.util.zip.CRC32;
//
//
//@Configuration
//public class ShardDBConfig {
//    @Bean
//    public DataSource shardDataSource() throws SQLException {
//        Map<String, DataSource> dataSources = Map.of(
//                "shard-0", createDataSource("jdbc:mysql://svc.gksl2.cloudtype.app:32419/shardtest", "root", "1234"),
//                "shard-1", createDataSource("jdbc:mysql://svc.gksl2.cloudtype.app:31209/shardtest", "root", "System12341234@")
//        );
//
//        PreciseShardingAlgorithm<?> shardingAlgorithm = new CustomShardingAlgorithm(dataSources.size());
//
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        shardingRuleConfig.setTableRuleConfigs(Arrays.asList(
//                createTableRuleConfig("User", "userSeq", "type", shardingAlgorithm)
//        ));
//
//        return ShardingDataSourceFactory.createDataSource(dataSources, shardingRuleConfig, new Properties());
//    }
//
//    private DataSource createDataSource(String url, String username, String password) {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
//        dataSource.setJdbcUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        return dataSource;
//    }
//
//    private TableRuleConfiguration createTableRuleConfig(String tableName, String primaryKeyName, String shardingKeyName, PreciseShardingAlgorithm shardingAlgorithm) {
//        Properties properties = new Properties();
//        properties.setProperty("worker.id", "5051"); // 프로세스별로 다른 ID값으로 지정
//
//        TableRuleConfiguration tableRuleConfig = new TableRuleConfiguration(tableName);
//        tableRuleConfig.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", primaryKeyName, properties));
//        tableRuleConfig.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration(shardingKeyName, shardingAlgorithm));
//
//        return tableRuleConfig;
//    }
//
//    @Slf4j
//    @RequiredArgsConstructor
//    public static class CustomShardingAlgorithm<T extends Comparable<?>> implements PreciseShardingAlgorithm<T> {
//        private final int shardSize;
//
//        @Override
//        public String doSharding(final Collection<String> shardNames, final PreciseShardingValue<T> shardingValue) {
//            String shardName = "shard-" + Math.abs(crc32(String.valueOf(shardingValue.getValue()))) % shardSize;
//
//            log.info("[DO_SHARDING] shardName : {}, shardingValue : {}", shardName, shardingValue);
//
//            if (shardNames.contains(shardName)) {
//                return shardName;
//            }
//
//            throw new UnsupportedOperationException();
//        }
//
//        private long crc32(String value) {
//            CRC32 crc32 = new CRC32();
//            crc32.update(value.getBytes());
//            return crc32.getValue();
//        }
//    }
//}
//
////@Configuration
////public class ShardDBConfig {
////    @Bean
////    public DataSource shardDataSource() throws SQLException {
////        Map<String, DataSource> dataSources = Map.of(
////                "shard-0", createDataSource("jdbc:mysql://svc.gksl2.cloudtype.app:32419/shardtest", "root", "1234"),
////                "shard-1", createDataSource("jdbc:mysql://svc.gksl2.cloudtype.app:31209/shardtest", "root", "System12341234@")
////        );
////
////        DataSourceRule dataSourceRule = new DataSourceRule(dataSources);
////
////        SingleKeyDatabaseShardingAlgorithm<?> shardingAlgorithm = new CustomShardingAlgorithm<>(dataSources.size());
////
////        ShardingRule shardingRule = new ShardingRule.ShardingRuleBuilder()
////                .dataSourceRule(dataSourceRule)
////                .tableRules(Arrays.asList(
////                        createTableRule("User", "userSeq", "type", dataSourceRule, shardingAlgorithm)
////                ))
////                .build();
////
////        return new ShardingDataSource(shardingRule);
////    }
////
////    private DataSource createDataSource(String url, String username, String password) {
////        HikariDataSource dataSource = new HikariDataSource();
////        dataSource.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
////        dataSource.setJdbcUrl(url);
////        dataSource.setUsername(username);
////        dataSource.setPassword(password);
////        return dataSource;
////    }
////
////    private TableRule createTableRule(String tableName, String primaryKeyName, String shardingKeyName, DataSourceRule dataSourceRule, SingleKeyDatabaseShardingAlgorithm<?> shardingAlgorithm) {
////        return TableRule.builder(tableName)
////                .generateKeyColumn(primaryKeyName)
////                .databaseShardingStrategy(new DatabaseShardingStrategy(shardingKeyName, shardingAlgorithm))
////                .dataSourceRule(dataSourceRule)
////                .build();
////    }
////
////    @Slf4j
////    @RequiredArgsConstructor
////    private static class CustomShardingAlgorithm<T extends Comparable<?>> implements SingleKeyDatabaseShardingAlgorithm<T> {
////        private final int shardSize;
////
////        @Override
////        public String doEqualSharding(Collection<String> shardNames, ShardingValue<T> shardingValue) {
////            String shardName = "shard-" + Math.abs(crc32(String.valueOf(shardingValue.getValue()))) % shardSize;
////
////            log.info("[DO_SHARDING] shardName : {}, shardingValue : {}", shardName, shardingValue);
////
////            if (shardNames.contains(shardName)) {
////                return shardName;
////            }
////
////            throw new UnsupportedOperationException();
////        }
////
////        @Override
////        public Collection<String> doInSharding(Collection<String> shardNames, ShardingValue<T> shardingValue) {
////            return null;
////        }
////
////        @Override
////        public Collection<String> doBetweenSharding(Collection<String> shardNames, ShardingValue<T> shardingValue) {
////            return null;
////        }
////
////        // 간단하게 value.hashCode()를 사용할 수도 있지만
////        // 환경에 따라 hashCode() 값을 동일하게 반환해주지 않을 수 있으므로 샤딩에서 사용하지 않을 것을 권장
////        // 따라서 여기서는 CRC32를 활용하여 hash 계산
////        private long crc32(String value) {
////            CRC32 crc32 = new CRC32();
////            crc32.update(value.getBytes());
////            return crc32.getValue();
////        }
////    }
////}
