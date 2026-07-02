-- product_ddl_update.sql
-- 기존 products 정정 + 한국어 실제 재료명 추가
-- 9000개를 억지 생성하지 않고, 실제 존재하는 한국어 재료명만 가능한 선에서 추가
BEGIN;

-- FK 참조가 있는 삭제 대상 먼저 정리
DELETE FROM member_avoid_products WHERE product_id BETWEEN 1412 AND 1447;
DELETE FROM products WHERE product_id BETWEEN 1412 AND 1447;

-- 기존 재료 카테고리/유통기한 정정
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1402;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 1401;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1400;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1399;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1398;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1397;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1396;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1395;
UPDATE products SET product_category_id = 5, default_expiry_days = 14, is_active = true WHERE product_id = 1394;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1393;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1392;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1391;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1390;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1389;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1388;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1387;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1386;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1385;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1384;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1383;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1382;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1381;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1380;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1379;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1378;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1377;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1376;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1375;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1374;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1373;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1372;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1371;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1370;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1369;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1368;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1367;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1366;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1365;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1364;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1363;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1362;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1361;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1360;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1359;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1358;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1357;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1356;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1355;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1354;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1353;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1352;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1351;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1350;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1349;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1348;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 1347;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1346;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1345;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1344;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1343;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1342;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1341;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1340;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1339;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1338;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1337;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1336;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1335;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1334;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1333;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1332;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1331;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1330;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1329;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1328;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1327;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 1326;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1325;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1324;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1323;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1322;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1321;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1320;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1319;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1318;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1317;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1316;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1315;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1314;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1313;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1312;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 1311;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 1310;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1309;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1308;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1307;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 1306;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1305;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1304;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 1303;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 1302;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1301;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1300;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1299;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1298;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1297;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1296;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1295;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1294;
UPDATE products SET product_category_id = 5, default_expiry_days = 60, is_active = true WHERE product_id = 1293;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1292;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1291;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1290;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1289;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1288;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1287;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1286;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1285;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1284;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1283;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1282;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1281;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1280;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 1279;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1278;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 1277;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1276;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1275;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1274;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1273;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1272;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1271;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1270;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1269;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1268;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1267;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1266;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1265;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1264;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1263;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1262;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1261;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1260;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 1259;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1258;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1257;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 1256;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1255;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1254;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1253;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1252;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1251;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1250;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 1249;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1248;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1247;
UPDATE products SET product_category_id = 9, default_expiry_days = 180, is_active = true WHERE product_id = 1246;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1245;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1244;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1243;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1242;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1241;
UPDATE products SET product_category_id = 9, default_expiry_days = 180, is_active = true WHERE product_id = 1240;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1239;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1238;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1237;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1236;
UPDATE products SET product_category_id = 5, default_expiry_days = 14, is_active = true WHERE product_id = 1235;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1234;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1233;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1232;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1231;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1230;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1229;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1228;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1227;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1226;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1225;
UPDATE products SET product_category_id = 5, default_expiry_days = 21, is_active = true WHERE product_id = 1224;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1223;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1222;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1221;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1220;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1219;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1218;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1217;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1216;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1215;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1214;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1213;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1212;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1211;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1210;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1209;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1208;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1207;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 1206;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1205;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 1204;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1203;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1202;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1201;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1199;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1198;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 1197;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1196;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1195;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1194;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1193;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1192;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1191;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1190;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1187;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1186;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1185;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 1184;
UPDATE products SET product_category_id = 5, default_expiry_days = 21, is_active = true WHERE product_id = 1183;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1182;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1181;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1180;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1179;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1178;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1177;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1176;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1175;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1174;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1173;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1172;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1171;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1170;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1169;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1168;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1167;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1166;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 1165;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1164;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 1163;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1162;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1161;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1160;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1159;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1158;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1157;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1156;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1155;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1154;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1153;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1152;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1151;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1150;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 1149;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1148;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1147;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1146;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1145;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1144;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1143;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1142;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1141;
UPDATE products SET product_category_id = 5, default_expiry_days = 14, is_active = true WHERE product_id = 1140;
UPDATE products SET product_category_id = 5, default_expiry_days = 14, is_active = true WHERE product_id = 1139;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1138;
UPDATE products SET product_category_id = 9, default_expiry_days = 180, is_active = true WHERE product_id = 1137;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1136;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1135;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1134;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1133;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 1132;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1131;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1130;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1129;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1128;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1127;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1126;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1125;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1124;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1123;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 1122;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1121;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1120;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1119;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1118;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1117;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1116;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1115;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1114;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1113;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1112;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1111;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1110;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1109;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1108;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1107;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1106;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 1105;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 1104;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1103;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1102;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 1101;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1100;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1099;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1098;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1097;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 1096;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1095;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1094;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1093;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1092;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1091;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1090;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1089;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1088;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1087;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1086;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1085;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1084;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1083;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 1082;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1081;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 1080;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1079;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1078;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1077;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1076;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1075;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1074;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1073;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1072;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1071;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 1070;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1069;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1068;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1067;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1066;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1065;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1064;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1063;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1062;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1061;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1060;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1059;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1058;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1057;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1056;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1055;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1054;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1053;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1052;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1051;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1050;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1049;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1048;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1047;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 1046;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1045;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1044;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1043;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1042;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1041;
UPDATE products SET product_category_id = 5, default_expiry_days = 60, is_active = true WHERE product_id = 1040;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1039;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1038;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1037;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1036;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1035;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1034;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1033;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1032;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1031;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1030;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1029;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 1028;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1027;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1026;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1025;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1024;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1023;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1022;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1021;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1020;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1019;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1018;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1017;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1016;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 1015;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1014;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1013;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1012;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1011;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1010;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1009;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 1008;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1007;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1006;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1005;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 1004;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1003;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 1002;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1001;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 1000;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 999;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 998;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 997;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 996;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 995;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 994;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 993;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 992;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 991;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 990;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 989;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 988;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 987;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 986;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 985;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 984;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 983;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 982;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 981;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 980;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 979;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 978;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 977;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 976;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 975;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 974;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 973;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 972;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 971;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 970;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 968;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 967;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 966;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 965;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 964;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 963;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 962;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 961;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 960;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 959;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 958;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 957;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 956;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 955;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 954;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 953;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 952;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 951;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 950;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 949;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 948;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 947;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 946;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 945;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 944;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 943;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 942;
UPDATE products SET product_category_id = 5, default_expiry_days = 14, is_active = true WHERE product_id = 941;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 940;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 939;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 938;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 937;
UPDATE products SET product_category_id = 5, default_expiry_days = 60, is_active = true WHERE product_id = 936;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 935;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 934;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 933;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 932;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 931;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 930;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 929;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 928;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 927;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 926;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 925;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 924;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 923;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 922;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 921;
UPDATE products SET product_category_id = 5, default_expiry_days = 14, is_active = true WHERE product_id = 920;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 919;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 918;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 917;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 916;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 915;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 914;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 913;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 912;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 911;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 910;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 909;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 908;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 907;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 906;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 905;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 904;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 903;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 902;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 901;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 900;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 899;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 898;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 897;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 896;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 895;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 894;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 893;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 892;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 891;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 890;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 889;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 888;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 887;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 886;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 885;
UPDATE products SET product_category_id = 5, default_expiry_days = 14, is_active = true WHERE product_id = 884;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 883;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 882;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 881;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 880;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 879;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 878;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 877;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 876;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 875;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 874;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 873;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 872;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 871;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 870;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 869;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 868;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 867;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 866;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 865;
UPDATE products SET product_category_id = 9, default_expiry_days = 180, is_active = true WHERE product_id = 864;
UPDATE products SET product_category_id = 5, default_expiry_days = 21, is_active = true WHERE product_id = 863;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 862;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 861;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 860;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 859;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 858;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 857;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 856;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 855;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 854;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 853;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 852;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 851;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 850;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 849;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 848;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 847;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 846;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 845;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 844;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 843;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 842;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 841;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 840;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 839;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 838;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 837;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 836;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 835;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 834;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 833;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 832;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 831;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 830;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 829;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 828;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 827;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 826;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 825;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 824;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 823;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 822;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 821;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 820;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 819;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 818;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 817;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 816;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 815;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 814;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 813;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 812;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 811;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 810;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 809;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 808;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 807;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 806;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 805;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 804;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 803;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 802;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 801;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 800;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 799;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 798;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 797;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 796;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 795;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 794;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 793;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 792;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 791;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 790;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 789;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 788;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 787;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 786;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 785;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 784;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 783;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 782;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 781;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 780;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 779;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 778;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 776;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 775;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 774;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 773;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 772;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 771;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 770;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 769;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 768;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 767;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 766;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 765;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 764;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 763;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 762;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 761;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 760;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 759;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 758;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 757;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 756;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 755;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 754;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 753;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 752;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 751;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 750;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 749;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 748;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 747;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 746;
UPDATE products SET product_category_id = 9, default_expiry_days = 180, is_active = true WHERE product_id = 745;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 744;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 743;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 742;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 741;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 740;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 739;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 738;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 736;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 735;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 734;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 733;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 732;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 731;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 730;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 729;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 728;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 727;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 726;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 725;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 724;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 723;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 722;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 721;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 720;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 719;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 718;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 717;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 716;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 715;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 714;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 713;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 712;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 711;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 710;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 709;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 708;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 707;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 706;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 705;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 704;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 703;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 702;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 701;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 700;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 699;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 698;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 697;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 696;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 695;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 694;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 693;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 692;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 691;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 690;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 689;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 688;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 687;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 686;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 685;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 684;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 683;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 682;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 681;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 680;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 679;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 678;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 677;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 676;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 675;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 674;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 673;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 672;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 671;
UPDATE products SET product_category_id = 5, default_expiry_days = 14, is_active = true WHERE product_id = 670;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 669;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 668;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 667;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 666;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 665;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 664;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 663;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 662;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 661;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 660;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 659;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 658;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 657;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 656;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 655;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 654;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 653;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 652;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 651;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 650;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 649;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 648;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 647;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 646;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 645;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 644;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 643;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 642;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 641;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 640;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 639;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 638;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 637;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 636;
UPDATE products SET product_category_id = 5, default_expiry_days = 14, is_active = true WHERE product_id = 635;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 634;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 633;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 632;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 631;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 630;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 629;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 628;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 627;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 626;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 625;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 624;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 623;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 622;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 621;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 620;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 619;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 618;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 617;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 616;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 615;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 614;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 613;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 612;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 611;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 610;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 609;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 608;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 607;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 606;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 605;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 604;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 603;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 602;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 601;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 600;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 599;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 598;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 597;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 596;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 595;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 594;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 593;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 592;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 591;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 590;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 589;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 588;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 587;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 586;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 585;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 584;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 583;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 582;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 581;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 580;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 579;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 578;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 577;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 576;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 575;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 574;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 573;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 572;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 571;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 570;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 569;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 568;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 567;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 566;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 565;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 564;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 563;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 562;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 561;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 560;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 559;
UPDATE products SET product_category_id = 5, default_expiry_days = 14, is_active = true WHERE product_id = 558;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 557;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 556;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 555;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 554;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 553;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 552;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 551;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 550;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 549;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 548;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 547;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 546;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 545;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 544;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 543;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 542;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 541;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 540;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 539;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 538;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 537;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 536;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 535;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 534;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 533;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 532;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 531;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 530;
UPDATE products SET product_category_id = 9, default_expiry_days = 180, is_active = true WHERE product_id = 529;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 528;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 527;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 526;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 525;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 524;
UPDATE products SET product_category_id = 9, default_expiry_days = 180, is_active = true WHERE product_id = 523;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 522;
UPDATE products SET product_category_id = 5, default_expiry_days = 14, is_active = true WHERE product_id = 521;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 520;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 519;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 518;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 517;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 516;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 515;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 514;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 513;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 512;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 511;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 510;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 509;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 508;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 507;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 506;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 505;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 504;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 503;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 502;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 501;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 500;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 499;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 498;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 497;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 496;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 495;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 494;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 493;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 492;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 491;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 490;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 489;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 488;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 487;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 486;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 485;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 484;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 483;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 482;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 481;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 480;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 479;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 478;
UPDATE products SET product_category_id = 9, default_expiry_days = 180, is_active = true WHERE product_id = 477;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 476;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 475;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 474;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 473;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 472;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 471;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 470;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 469;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 468;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 467;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 466;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 465;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 464;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 463;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 462;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 461;
UPDATE products SET product_category_id = 9, default_expiry_days = 180, is_active = true WHERE product_id = 460;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 459;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 458;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 457;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 456;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 455;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 454;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 453;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 452;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 451;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 450;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 449;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 448;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 447;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 446;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 445;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 444;
UPDATE products SET product_category_id = 9, default_expiry_days = 180, is_active = true WHERE product_id = 443;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 442;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 441;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 440;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 439;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 438;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 437;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 436;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 435;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 434;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 433;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 432;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 431;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 430;
UPDATE products SET product_category_id = 5, default_expiry_days = 21, is_active = true WHERE product_id = 429;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 428;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 427;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 426;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 425;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 424;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 423;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 422;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 421;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 420;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 419;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 418;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 417;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 416;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 415;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 414;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 413;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 412;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 411;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 410;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 409;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 408;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 407;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 406;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 405;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 404;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 403;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 402;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 401;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 400;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 399;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 398;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 397;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 396;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 395;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 394;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 393;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 392;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 391;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 390;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 389;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 388;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 387;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 386;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 385;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 384;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 383;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 382;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 381;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 380;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 379;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 378;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 377;
UPDATE products SET product_category_id = 5, default_expiry_days = 14, is_active = true WHERE product_id = 376;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 375;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 374;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 373;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 372;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 371;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 370;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 369;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 368;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 367;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 366;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 365;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 364;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 363;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 362;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 361;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 360;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 359;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 358;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 357;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 356;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 355;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 354;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 353;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 352;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 351;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 350;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 349;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 348;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 347;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 346;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 345;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 344;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 343;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 342;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 341;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 340;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 339;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 338;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 337;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 336;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 335;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 334;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 333;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 332;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 331;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 330;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 329;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 328;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 327;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 326;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 325;
UPDATE products SET product_category_id = 5, default_expiry_days = 14, is_active = true WHERE product_id = 324;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 323;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 322;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 321;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 320;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 319;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 318;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 317;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 316;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 315;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 314;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 313;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 312;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 311;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 310;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 309;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 308;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 307;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 306;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 305;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 304;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 303;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 302;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 301;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 300;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 299;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 298;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 297;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 296;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 295;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 294;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 293;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 292;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 291;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 290;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 289;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 288;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 287;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 286;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 285;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 284;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 283;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 282;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 281;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 280;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 279;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 278;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 277;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 276;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 275;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 274;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 273;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 272;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 271;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 270;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 269;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 268;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 267;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 266;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 265;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 264;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 263;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 262;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 261;
UPDATE products SET product_category_id = 5, default_expiry_days = 14, is_active = true WHERE product_id = 260;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 259;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 258;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 257;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 256;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 255;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 254;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 253;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 252;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 251;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 250;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 249;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 248;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 247;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 246;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 245;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 244;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 243;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 242;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 241;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 240;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 239;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 238;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 237;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 236;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 235;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 234;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 233;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 232;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 231;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 230;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 229;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 228;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 227;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 226;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 225;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 224;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 223;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 222;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 221;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 220;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 219;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 218;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 217;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 216;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 215;
UPDATE products SET product_category_id = 5, default_expiry_days = 30, is_active = true WHERE product_id = 214;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 213;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 212;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 211;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 210;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 209;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 208;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 207;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 206;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 205;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 204;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 203;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 202;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 201;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 200;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 199;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 198;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 197;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 196;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 195;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 194;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 193;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 192;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 191;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 190;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 189;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 188;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 187;
UPDATE products SET product_category_id = 9, default_expiry_days = 180, is_active = true WHERE product_id = 186;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 185;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 184;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 183;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 182;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 181;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 180;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 179;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 178;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 177;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 176;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 175;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 174;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 173;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 172;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 171;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 170;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 169;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 168;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 167;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 166;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 165;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 164;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 163;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 162;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 161;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 160;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 159;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 158;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 157;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 156;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 155;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 154;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 153;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 152;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 151;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 150;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 149;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 148;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 147;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 146;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 145;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 144;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 143;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 142;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 141;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 140;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 139;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 138;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 137;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 136;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 135;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 134;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 133;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 132;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 131;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 130;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 129;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 128;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 127;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 126;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 125;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 124;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 123;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 122;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 121;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 120;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 119;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 118;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 117;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 116;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 115;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 114;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 113;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 112;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 111;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 110;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 109;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 108;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 107;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 106;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 105;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 104;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 103;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 102;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 101;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 100;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 99;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 98;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 97;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 96;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 95;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 94;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 93;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 92;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 91;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 90;
UPDATE products SET product_category_id = 3, default_expiry_days = 3, is_active = true WHERE product_id = 89;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 88;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 87;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 86;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 85;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 84;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 83;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 82;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 81;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 80;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 79;
UPDATE products SET product_category_id = 6, default_expiry_days = 180, is_active = true WHERE product_id = 78;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 77;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 76;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 75;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 74;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 73;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 72;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 71;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 70;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 69;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 68;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 67;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 66;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 65;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 64;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 63;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 62;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 61;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 60;
UPDATE products SET product_category_id = 9, default_expiry_days = 180, is_active = true WHERE product_id = 59;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 58;
UPDATE products SET product_category_id = 5, default_expiry_days = 14, is_active = true WHERE product_id = 57;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 56;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 55;
UPDATE products SET product_category_id = 5, default_expiry_days = 14, is_active = true WHERE product_id = 54;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 53;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 52;
UPDATE products SET product_category_id = 5, default_expiry_days = 21, is_active = true WHERE product_id = 51;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 50;
UPDATE products SET product_category_id = 5, default_expiry_days = 14, is_active = true WHERE product_id = 49;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 48;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 47;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 46;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 45;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 44;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 43;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 42;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 41;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 40;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 39;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 38;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 37;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 36;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 35;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 34;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 33;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 32;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 31;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 30;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 29;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 28;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 27;
UPDATE products SET product_category_id = 2, default_expiry_days = 10, is_active = true WHERE product_id = 26;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 25;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 24;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 23;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 22;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 21;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 20;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 19;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 18;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 17;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 16;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 15;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 14;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 13;
UPDATE products SET product_category_id = 1, default_expiry_days = 7, is_active = true WHERE product_id = 12;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 11;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 10;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 9;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 8;
UPDATE products SET product_category_id = 10, default_expiry_days = 30, is_active = true WHERE product_id = 7;
UPDATE products SET product_category_id = 5, default_expiry_days = 60, is_active = true WHERE product_id = 6;
UPDATE products SET product_category_id = 8, default_expiry_days = 365, is_active = true WHERE product_id = 5;
UPDATE products SET product_category_id = 5, default_expiry_days = 14, is_active = true WHERE product_id = 4;
UPDATE products SET product_category_id = 5, default_expiry_days = 21, is_active = true WHERE product_id = 3;
UPDATE products SET product_category_id = 4, default_expiry_days = 2, is_active = true WHERE product_id = 2;
UPDATE products SET product_category_id = 7, default_expiry_days = 180, is_active = true WHERE product_id = 1;

-- 신규 한국어 실제 재료명 추가
INSERT INTO products (product_id, product_category_id, name, default_expiry_days, is_active, created_at, updated_at) VALUES
(1403, 1, '적상추', 7, true, '2026-07-01 12:00:00.000', NULL),
(1404, 1, '방울양배추', 7, true, '2026-07-01 12:00:00.000', NULL),
(1405, 1, '비타민채', 7, true, '2026-07-01 12:00:00.000', NULL),
(1406, 1, '돌미나리', 7, true, '2026-07-01 12:00:00.000', NULL),
(1407, 1, '딜', 7, true, '2026-07-01 12:00:00.000', NULL),
(1408, 1, '세이지', 7, true, '2026-07-01 12:00:00.000', NULL),
(1409, 1, '샬롯', 7, true, '2026-07-01 12:00:00.000', NULL),
(1410, 1, '미니당근', 7, true, '2026-07-01 12:00:00.000', NULL),
(1411, 1, '총각무', 7, true, '2026-07-01 12:00:00.000', NULL),
(1412, 1, '마', 7, true, '2026-07-01 12:00:00.000', NULL),
(1413, 1, '돼지감자', 7, true, '2026-07-01 12:00:00.000', NULL),
(1414, 1, '야콘', 7, true, '2026-07-01 12:00:00.000', NULL),
(1415, 1, '카사바', 7, true, '2026-07-01 12:00:00.000', NULL),
(1416, 1, '백오이', 7, true, '2026-07-01 12:00:00.000', NULL),
(1417, 1, '가시오이', 7, true, '2026-07-01 12:00:00.000', NULL),
(1418, 1, '노각', 7, true, '2026-07-01 12:00:00.000', NULL),
(1419, 1, '주키니', 7, true, '2026-07-01 12:00:00.000', NULL),
(1420, 1, '늙은호박', 7, true, '2026-07-01 12:00:00.000', NULL),
(1421, 1, '풋호박', 7, true, '2026-07-01 12:00:00.000', NULL),
(1422, 1, '대추방울토마토', 7, true, '2026-07-01 12:00:00.000', NULL),
(1423, 1, '씀바귀', 7, true, '2026-07-01 12:00:00.000', NULL),
(1424, 1, '엄나무순', 7, true, '2026-07-01 12:00:00.000', NULL),
(1425, 1, '방풍나물', 7, true, '2026-07-01 12:00:00.000', NULL),
(1426, 1, '숙주나물', 7, true, '2026-07-01 12:00:00.000', NULL),
(1427, 1, '배추김치용배추', 7, true, '2026-07-01 12:00:00.000', NULL),
(1428, 1, '송이버섯', 7, true, '2026-07-01 12:00:00.000', NULL),
(1429, 1, '능이버섯', 7, true, '2026-07-01 12:00:00.000', NULL),
(1430, 1, '포르치니버섯', 7, true, '2026-07-01 12:00:00.000', NULL),
(1431, 1, '트러플', 7, true, '2026-07-01 12:00:00.000', NULL),
(1432, 1, '버터헤드레터스', 7, true, '2026-07-01 12:00:00.000', NULL),
(1433, 1, '엔다이브', 7, true, '2026-07-01 12:00:00.000', NULL),
(1434, 1, '비름', 7, true, '2026-07-01 12:00:00.000', NULL),
(1435, 1, '아티초크', 7, true, '2026-07-01 12:00:00.000', NULL),
(1436, 1, '펜넬', 7, true, '2026-07-01 12:00:00.000', NULL),
(1437, 1, '오크라', 7, true, '2026-07-01 12:00:00.000', NULL),
(1438, 1, '루타바가', 7, true, '2026-07-01 12:00:00.000', NULL),
(1439, 1, '파스닙', 7, true, '2026-07-01 12:00:00.000', NULL),
(1440, 1, '물냉이', 7, true, '2026-07-01 12:00:00.000', NULL),
(1441, 1, '스위스차드', 7, true, '2026-07-01 12:00:00.000', NULL),
(1442, 1, '레디시', 7, true, '2026-07-01 12:00:00.000', NULL),
(1443, 1, '겨자잎', 7, true, '2026-07-01 12:00:00.000', NULL),
(1444, 1, '갓', 7, true, '2026-07-01 12:00:00.000', NULL),
(1445, 1, '유채나물', 7, true, '2026-07-01 12:00:00.000', NULL),
(1446, 1, '메밀순', 7, true, '2026-07-01 12:00:00.000', NULL),
(1447, 1, '보리순', 7, true, '2026-07-01 12:00:00.000', NULL),
(1448, 1, '완두순', 7, true, '2026-07-01 12:00:00.000', NULL),
(1449, 1, '양하', 7, true, '2026-07-01 12:00:00.000', NULL),
(1450, 1, '명이나물', 7, true, '2026-07-01 12:00:00.000', NULL),
(1451, 1, '고들빼기', 7, true, '2026-07-01 12:00:00.000', NULL),
(1452, 1, '질경이', 7, true, '2026-07-01 12:00:00.000', NULL),
(1453, 1, '깻순', 7, true, '2026-07-01 12:00:00.000', NULL),
(1454, 1, '고춧잎', 7, true, '2026-07-01 12:00:00.000', NULL),
(1455, 1, '고구마잎', 7, true, '2026-07-01 12:00:00.000', NULL),
(1456, 1, '당근잎', 7, true, '2026-07-01 12:00:00.000', NULL),
(1457, 1, '비트잎', 7, true, '2026-07-01 12:00:00.000', NULL),
(1458, 2, '아오리사과', 10, true, '2026-07-01 12:00:00.000', NULL),
(1459, 2, '신고배', 10, true, '2026-07-01 12:00:00.000', NULL),
(1460, 2, '원황배', 10, true, '2026-07-01 12:00:00.000', NULL),
(1461, 2, '천혜향', 10, true, '2026-07-01 12:00:00.000', NULL),
(1462, 2, '레드향', 10, true, '2026-07-01 12:00:00.000', NULL),
(1463, 2, '황금향', 10, true, '2026-07-01 12:00:00.000', NULL),
(1464, 2, '블러드오렌지', 10, true, '2026-07-01 12:00:00.000', NULL),
(1465, 2, '금귤', 10, true, '2026-07-01 12:00:00.000', NULL),
(1466, 2, '애플망고', 10, true, '2026-07-01 12:00:00.000', NULL),
(1467, 2, '그린망고', 10, true, '2026-07-01 12:00:00.000', NULL),
(1468, 2, '파파야', 10, true, '2026-07-01 12:00:00.000', NULL),
(1469, 2, '구아바', 10, true, '2026-07-01 12:00:00.000', NULL),
(1470, 2, '패션프루트', 10, true, '2026-07-01 12:00:00.000', NULL),
(1471, 2, '람부탄', 10, true, '2026-07-01 12:00:00.000', NULL),
(1472, 2, '망고스틴', 10, true, '2026-07-01 12:00:00.000', NULL),
(1473, 2, '용과', 10, true, '2026-07-01 12:00:00.000', NULL),
(1474, 2, '두리안', 10, true, '2026-07-01 12:00:00.000', NULL),
(1475, 2, '잭프루트', 10, true, '2026-07-01 12:00:00.000', NULL),
(1476, 2, '스타프루트', 10, true, '2026-07-01 12:00:00.000', NULL),
(1477, 2, '골드키위', 10, true, '2026-07-01 12:00:00.000', NULL),
(1478, 2, '그린키위', 10, true, '2026-07-01 12:00:00.000', NULL),
(1479, 2, '애플수박', 10, true, '2026-07-01 12:00:00.000', NULL),
(1480, 2, '머스크멜론', 10, true, '2026-07-01 12:00:00.000', NULL),
(1481, 2, '허니듀멜론', 10, true, '2026-07-01 12:00:00.000', NULL),
(1482, 2, '칸탈루프', 10, true, '2026-07-01 12:00:00.000', NULL),
(1483, 2, '블랙베리', 10, true, '2026-07-01 12:00:00.000', NULL),
(1484, 2, '아로니아', 10, true, '2026-07-01 12:00:00.000', NULL),
(1485, 2, '앵두', 10, true, '2026-07-01 12:00:00.000', NULL),
(1486, 2, '거봉', 10, true, '2026-07-01 12:00:00.000', NULL),
(1487, 2, '샤인머스캣', 10, true, '2026-07-01 12:00:00.000', NULL),
(1488, 2, '캠벨포도', 10, true, '2026-07-01 12:00:00.000', NULL),
(1489, 2, '머루', 10, true, '2026-07-01 12:00:00.000', NULL),
(1490, 2, '백도', 10, true, '2026-07-01 12:00:00.000', NULL),
(1491, 2, '플럼', 10, true, '2026-07-01 12:00:00.000', NULL),
(1492, 2, '넥타린', 10, true, '2026-07-01 12:00:00.000', NULL),
(1493, 2, '생대추', 10, true, '2026-07-01 12:00:00.000', NULL),
(1494, 2, '말린대추', 10, true, '2026-07-01 12:00:00.000', NULL),
(1495, 2, '코코넛', 10, true, '2026-07-01 12:00:00.000', NULL),
(1496, 2, '오디', 10, true, '2026-07-01 12:00:00.000', NULL),
(1497, 2, '보리수', 10, true, '2026-07-01 12:00:00.000', NULL),
(1498, 2, '구스베리', 10, true, '2026-07-01 12:00:00.000', NULL),
(1499, 2, '블랙커런트', 10, true, '2026-07-01 12:00:00.000', NULL),
(1500, 2, '레드커런트', 10, true, '2026-07-01 12:00:00.000', NULL),
(1501, 2, '엘더베리', 10, true, '2026-07-01 12:00:00.000', NULL),
(1502, 2, '클레멘타인', 10, true, '2026-07-01 12:00:00.000', NULL),
(1503, 2, '만다린', 10, true, '2026-07-01 12:00:00.000', NULL),
(1504, 2, '플루오트', 10, true, '2026-07-01 12:00:00.000', NULL),
(1505, 2, '카람볼라', 10, true, '2026-07-01 12:00:00.000', NULL),
(1506, 2, '롱간', 10, true, '2026-07-01 12:00:00.000', NULL),
(1507, 2, '청귤', 10, true, '2026-07-01 12:00:00.000', NULL),
(1508, 2, '건자두', 10, true, '2026-07-01 12:00:00.000', NULL),
(1509, 2, '말린무화과', 10, true, '2026-07-01 12:00:00.000', NULL),
(1510, 2, '말린망고', 10, true, '2026-07-01 12:00:00.000', NULL),
(1511, 3, '한우소고기', 3, true, '2026-07-01 12:00:00.000', NULL),
(1512, 3, '수입소고기', 3, true, '2026-07-01 12:00:00.000', NULL),
(1513, 3, '소안심', 3, true, '2026-07-01 12:00:00.000', NULL),
(1514, 3, '소채끝', 3, true, '2026-07-01 12:00:00.000', NULL),
(1515, 3, '소갈빗살', 3, true, '2026-07-01 12:00:00.000', NULL),
(1516, 3, '소부채살', 3, true, '2026-07-01 12:00:00.000', NULL),
(1517, 3, '소살치살', 3, true, '2026-07-01 12:00:00.000', NULL),
(1518, 3, '소토시살', 3, true, '2026-07-01 12:00:00.000', NULL),
(1519, 3, '소치마살', 3, true, '2026-07-01 12:00:00.000', NULL),
(1520, 3, '소업진살', 3, true, '2026-07-01 12:00:00.000', NULL),
(1521, 3, '소우둔', 3, true, '2026-07-01 12:00:00.000', NULL),
(1522, 3, '소홍두깨', 3, true, '2026-07-01 12:00:00.000', NULL),
(1523, 3, '소목심', 3, true, '2026-07-01 12:00:00.000', NULL),
(1524, 3, '소앞다리', 3, true, '2026-07-01 12:00:00.000', NULL),
(1525, 3, '소다짐육', 3, true, '2026-07-01 12:00:00.000', NULL),
(1526, 3, '소꼬리', 3, true, '2026-07-01 12:00:00.000', NULL),
(1527, 3, '소족', 3, true, '2026-07-01 12:00:00.000', NULL),
(1528, 3, '소간', 3, true, '2026-07-01 12:00:00.000', NULL),
(1529, 3, '소곱창', 3, true, '2026-07-01 12:00:00.000', NULL),
(1530, 3, '소대창', 3, true, '2026-07-01 12:00:00.000', NULL),
(1531, 3, '소막창', 3, true, '2026-07-01 12:00:00.000', NULL),
(1532, 3, '소양', 3, true, '2026-07-01 12:00:00.000', NULL),
(1533, 3, '소염통', 3, true, '2026-07-01 12:00:00.000', NULL),
(1534, 3, '돼지삼겹살', 3, true, '2026-07-01 12:00:00.000', NULL),
(1535, 3, '돼지앞다리살', 3, true, '2026-07-01 12:00:00.000', NULL),
(1536, 3, '돼지뒷다리살', 3, true, '2026-07-01 12:00:00.000', NULL),
(1537, 3, '돼지안심', 3, true, '2026-07-01 12:00:00.000', NULL),
(1538, 3, '돼지갈매기살', 3, true, '2026-07-01 12:00:00.000', NULL),
(1539, 3, '돼지항정살', 3, true, '2026-07-01 12:00:00.000', NULL),
(1540, 3, '돼지가브리살', 3, true, '2026-07-01 12:00:00.000', NULL),
(1541, 3, '돼지다짐육', 3, true, '2026-07-01 12:00:00.000', NULL),
(1542, 3, '돼지족발', 3, true, '2026-07-01 12:00:00.000', NULL),
(1543, 3, '돼지간', 3, true, '2026-07-01 12:00:00.000', NULL),
(1544, 3, '돼지곱창', 3, true, '2026-07-01 12:00:00.000', NULL),
(1545, 3, '돼지막창', 3, true, '2026-07-01 12:00:00.000', NULL),
(1546, 3, '돼지껍데기', 3, true, '2026-07-01 12:00:00.000', NULL),
(1547, 3, '닭안심', 3, true, '2026-07-01 12:00:00.000', NULL),
(1548, 3, '닭윙', 3, true, '2026-07-01 12:00:00.000', NULL),
(1549, 3, '닭근위', 3, true, '2026-07-01 12:00:00.000', NULL),
(1550, 3, '닭목살', 3, true, '2026-07-01 12:00:00.000', NULL),
(1551, 3, '닭다짐육', 3, true, '2026-07-01 12:00:00.000', NULL),
(1552, 3, '오리가슴살', 3, true, '2026-07-01 12:00:00.000', NULL),
(1553, 3, '오리다리살', 3, true, '2026-07-01 12:00:00.000', NULL),
(1554, 3, '양갈비', 3, true, '2026-07-01 12:00:00.000', NULL),
(1555, 3, '양등심', 3, true, '2026-07-01 12:00:00.000', NULL),
(1556, 3, '양어깨살', 3, true, '2026-07-01 12:00:00.000', NULL),
(1557, 3, '양다리살', 3, true, '2026-07-01 12:00:00.000', NULL),
(1558, 3, '양다짐육', 3, true, '2026-07-01 12:00:00.000', NULL),
(1559, 3, '염소고기', 3, true, '2026-07-01 12:00:00.000', NULL),
(1560, 3, '토끼고기', 3, true, '2026-07-01 12:00:00.000', NULL),
(1561, 3, '칠면조고기', 3, true, '2026-07-01 12:00:00.000', NULL),
(1562, 3, '칠면조가슴살', 3, true, '2026-07-01 12:00:00.000', NULL),
(1563, 3, '말고기', 3, true, '2026-07-01 12:00:00.000', NULL),
(1564, 3, '사슴고기', 3, true, '2026-07-01 12:00:00.000', NULL),
(1565, 3, '메추리고기', 3, true, '2026-07-01 12:00:00.000', NULL),
(1566, 3, '거위고기', 3, true, '2026-07-01 12:00:00.000', NULL),
(1567, 3, '프랑크소시지', 3, true, '2026-07-01 12:00:00.000', NULL),
(1568, 3, '비엔나소시지', 3, true, '2026-07-01 12:00:00.000', NULL),
(1569, 3, '초리조', 3, true, '2026-07-01 12:00:00.000', NULL),
(1570, 3, '프로슈토', 3, true, '2026-07-01 12:00:00.000', NULL),
(1571, 3, '판체타', 3, true, '2026-07-01 12:00:00.000', NULL),
(1572, 4, '정어리', 2, true, '2026-07-01 12:00:00.000', NULL),
(1573, 4, '대멸치', 2, true, '2026-07-01 12:00:00.000', NULL),
(1574, 4, '중멸치', 2, true, '2026-07-01 12:00:00.000', NULL),
(1575, 4, '생태', 2, true, '2026-07-01 12:00:00.000', NULL),
(1576, 4, '동태', 2, true, '2026-07-01 12:00:00.000', NULL),
(1577, 4, '참가자미', 2, true, '2026-07-01 12:00:00.000', NULL),
(1578, 4, '도다리', 2, true, '2026-07-01 12:00:00.000', NULL),
(1579, 4, '참돔', 2, true, '2026-07-01 12:00:00.000', NULL),
(1580, 4, '감성돔', 2, true, '2026-07-01 12:00:00.000', NULL),
(1581, 4, '굴비', 2, true, '2026-07-01 12:00:00.000', NULL),
(1582, 4, '임연수어', 2, true, '2026-07-01 12:00:00.000', NULL),
(1583, 4, '열기', 2, true, '2026-07-01 12:00:00.000', NULL),
(1584, 4, '볼락', 2, true, '2026-07-01 12:00:00.000', NULL),
(1585, 4, '부시리', 2, true, '2026-07-01 12:00:00.000', NULL),
(1586, 4, '전어', 2, true, '2026-07-01 12:00:00.000', NULL),
(1587, 4, '청어', 2, true, '2026-07-01 12:00:00.000', NULL),
(1588, 4, '전갱이', 2, true, '2026-07-01 12:00:00.000', NULL),
(1589, 4, '숭어', 2, true, '2026-07-01 12:00:00.000', NULL),
(1590, 4, '황새치', 2, true, '2026-07-01 12:00:00.000', NULL),
(1591, 4, '청새치', 2, true, '2026-07-01 12:00:00.000', NULL),
(1592, 4, '민물장어', 2, true, '2026-07-01 12:00:00.000', NULL),
(1593, 4, '바닷장어', 2, true, '2026-07-01 12:00:00.000', NULL),
(1594, 4, '홍어', 2, true, '2026-07-01 12:00:00.000', NULL),
(1595, 4, '삼식이', 2, true, '2026-07-01 12:00:00.000', NULL),
(1596, 4, '새꼬막', 2, true, '2026-07-01 12:00:00.000', NULL),
(1597, 4, '피꼬막', 2, true, '2026-07-01 12:00:00.000', NULL),
(1598, 4, '백합조개', 2, true, '2026-07-01 12:00:00.000', NULL),
(1599, 4, '동죽조개', 2, true, '2026-07-01 12:00:00.000', NULL),
(1600, 4, '석화', 2, true, '2026-07-01 12:00:00.000', NULL),
(1601, 4, '가리비', 2, true, '2026-07-01 12:00:00.000', NULL),
(1602, 4, '키조개', 2, true, '2026-07-01 12:00:00.000', NULL),
(1603, 4, '맛조개', 2, true, '2026-07-01 12:00:00.000', NULL),
(1604, 4, '대합', 2, true, '2026-07-01 12:00:00.000', NULL),
(1605, 4, '재첩', 2, true, '2026-07-01 12:00:00.000', NULL),
(1606, 4, '꽃새우', 2, true, '2026-07-01 12:00:00.000', NULL),
(1607, 4, '민물새우', 2, true, '2026-07-01 12:00:00.000', NULL),
(1608, 4, '대게', 2, true, '2026-07-01 12:00:00.000', NULL),
(1609, 4, '홍게', 2, true, '2026-07-01 12:00:00.000', NULL),
(1610, 4, '킹크랩', 2, true, '2026-07-01 12:00:00.000', NULL),
(1611, 4, '랍스터', 2, true, '2026-07-01 12:00:00.000', NULL),
(1612, 4, '한치', 2, true, '2026-07-01 12:00:00.000', NULL),
(1613, 4, '해삼', 2, true, '2026-07-01 12:00:00.000', NULL),
(1614, 4, '멍게', 2, true, '2026-07-01 12:00:00.000', NULL),
(1615, 4, '성게알', 2, true, '2026-07-01 12:00:00.000', NULL),
(1616, 4, '오만둥이', 2, true, '2026-07-01 12:00:00.000', NULL),
(1617, 4, '생미역', 2, true, '2026-07-01 12:00:00.000', NULL),
(1618, 4, '톳', 2, true, '2026-07-01 12:00:00.000', NULL),
(1619, 4, '돌김', 2, true, '2026-07-01 12:00:00.000', NULL),
(1620, 4, '구운김', 2, true, '2026-07-01 12:00:00.000', NULL),
(1621, 4, '청각', 2, true, '2026-07-01 12:00:00.000', NULL),
(1622, 4, '모자반', 2, true, '2026-07-01 12:00:00.000', NULL),
(1623, 4, '우뭇가사리', 2, true, '2026-07-01 12:00:00.000', NULL),
(1624, 5, '무지방우유', 14, true, '2026-07-01 12:00:00.000', NULL),
(1625, 5, '멸균우유', 14, true, '2026-07-01 12:00:00.000', NULL),
(1626, 5, '산양유', 14, true, '2026-07-01 12:00:00.000', NULL),
(1627, 5, '염소우유', 14, true, '2026-07-01 12:00:00.000', NULL),
(1628, 5, '무가당두유', 14, true, '2026-07-01 12:00:00.000', NULL),
(1629, 5, '아몬드밀크', 14, true, '2026-07-01 12:00:00.000', NULL),
(1630, 5, '오트밀크', 14, true, '2026-07-01 12:00:00.000', NULL),
(1631, 5, '고다치즈', 30, true, '2026-07-01 12:00:00.000', NULL),
(1632, 5, '브리치즈', 30, true, '2026-07-01 12:00:00.000', NULL),
(1633, 5, '카망베르치즈', 30, true, '2026-07-01 12:00:00.000', NULL),
(1634, 5, '페타치즈', 30, true, '2026-07-01 12:00:00.000', NULL),
(1635, 5, '블루치즈', 30, true, '2026-07-01 12:00:00.000', NULL),
(1636, 5, '에멘탈치즈', 30, true, '2026-07-01 12:00:00.000', NULL),
(1637, 5, '그뤼에르치즈', 30, true, '2026-07-01 12:00:00.000', NULL),
(1638, 5, '콜비잭치즈', 30, true, '2026-07-01 12:00:00.000', NULL),
(1639, 5, '몬터레이잭치즈', 30, true, '2026-07-01 12:00:00.000', NULL),
(1640, 5, '마스카포네치즈', 30, true, '2026-07-01 12:00:00.000', NULL),
(1641, 5, '코티지치즈', 30, true, '2026-07-01 12:00:00.000', NULL),
(1642, 5, '케피어', 14, true, '2026-07-01 12:00:00.000', NULL),
(1643, 5, '무염버터', 60, true, '2026-07-01 12:00:00.000', NULL),
(1644, 5, '가염버터', 60, true, '2026-07-01 12:00:00.000', NULL),
(1645, 5, '마가린', 14, true, '2026-07-01 12:00:00.000', NULL),
(1646, 5, '유정란', 14, true, '2026-07-01 12:00:00.000', NULL),
(1647, 5, '오리알', 21, true, '2026-07-01 12:00:00.000', NULL),
(1648, 5, '거위알', 21, true, '2026-07-01 12:00:00.000', NULL),
(1649, 5, '난황', 14, true, '2026-07-01 12:00:00.000', NULL),
(1650, 5, '난백', 14, true, '2026-07-01 12:00:00.000', NULL),
(1651, 5, '액상계란', 21, true, '2026-07-01 12:00:00.000', NULL),
(1652, 6, '늘보리', 180, true, '2026-07-01 12:00:00.000', NULL),
(1653, 6, '기장', 180, true, '2026-07-01 12:00:00.000', NULL),
(1654, 6, '조', 180, true, '2026-07-01 12:00:00.000', NULL),
(1655, 6, '메밀', 180, true, '2026-07-01 12:00:00.000', NULL),
(1656, 6, '아마란스', 180, true, '2026-07-01 12:00:00.000', NULL),
(1657, 6, '쿠스쿠스', 180, true, '2026-07-01 12:00:00.000', NULL),
(1658, 6, '불거', 180, true, '2026-07-01 12:00:00.000', NULL),
(1659, 6, '파로', 180, true, '2026-07-01 12:00:00.000', NULL),
(1660, 6, '스펠트', 180, true, '2026-07-01 12:00:00.000', NULL),
(1661, 6, '카무트', 180, true, '2026-07-01 12:00:00.000', NULL),
(1662, 6, '찰옥수수', 180, true, '2026-07-01 12:00:00.000', NULL),
(1663, 6, '감자전분', 180, true, '2026-07-01 12:00:00.000', NULL),
(1664, 6, '고구마전분', 180, true, '2026-07-01 12:00:00.000', NULL),
(1665, 6, '타피오카전분', 180, true, '2026-07-01 12:00:00.000', NULL),
(1666, 6, '중면', 180, true, '2026-07-01 12:00:00.000', NULL),
(1667, 6, '라면사리', 180, true, '2026-07-01 12:00:00.000', NULL),
(1668, 6, '녹두당면', 180, true, '2026-07-01 12:00:00.000', NULL),
(1669, 6, '링귀니', 180, true, '2026-07-01 12:00:00.000', NULL),
(1670, 6, '페투치네', 180, true, '2026-07-01 12:00:00.000', NULL),
(1671, 6, '마카로니', 180, true, '2026-07-01 12:00:00.000', NULL),
(1672, 6, '라자냐', 180, true, '2026-07-01 12:00:00.000', NULL),
(1673, 6, '리가토니', 180, true, '2026-07-01 12:00:00.000', NULL),
(1674, 6, '오르조', 180, true, '2026-07-01 12:00:00.000', NULL),
(1675, 6, '카펠리니', 180, true, '2026-07-01 12:00:00.000', NULL),
(1676, 6, '통밀빵', 180, true, '2026-07-01 12:00:00.000', NULL),
(1677, 6, '호밀빵', 180, true, '2026-07-01 12:00:00.000', NULL),
(1678, 6, '베이글', 180, true, '2026-07-01 12:00:00.000', NULL),
(1679, 6, '크루아상', 180, true, '2026-07-01 12:00:00.000', NULL),
(1680, 6, '모닝빵', 180, true, '2026-07-01 12:00:00.000', NULL),
(1681, 6, '난', 180, true, '2026-07-01 12:00:00.000', NULL),
(1682, 6, '피타브레드', 180, true, '2026-07-01 12:00:00.000', NULL),
(1683, 6, '잉글리시머핀', 180, true, '2026-07-01 12:00:00.000', NULL),
(1684, 6, '떡국떡', 180, true, '2026-07-01 12:00:00.000', NULL),
(1685, 6, '백설기', 180, true, '2026-07-01 12:00:00.000', NULL),
(1686, 6, '인절미', 180, true, '2026-07-01 12:00:00.000', NULL),
(1687, 7, '찌개두부', 180, true, '2026-07-01 12:00:00.000', NULL),
(1688, 7, '백태', 180, true, '2026-07-01 12:00:00.000', NULL),
(1689, 7, '녹두', 180, true, '2026-07-01 12:00:00.000', NULL),
(1690, 7, '적두', 180, true, '2026-07-01 12:00:00.000', NULL),
(1691, 7, '작두콩', 180, true, '2026-07-01 12:00:00.000', NULL),
(1692, 7, '동부콩', 180, true, '2026-07-01 12:00:00.000', NULL),
(1693, 7, '리마콩', 180, true, '2026-07-01 12:00:00.000', NULL),
(1694, 7, '핀토빈', 180, true, '2026-07-01 12:00:00.000', NULL),
(1695, 7, '키드니빈', 180, true, '2026-07-01 12:00:00.000', NULL),
(1696, 7, '병아리콩통조림', 180, true, '2026-07-01 12:00:00.000', NULL),
(1697, 7, '브라질너트', 180, true, '2026-07-01 12:00:00.000', NULL),
(1698, 7, '치아씨드', 180, true, '2026-07-01 12:00:00.000', NULL),
(1699, 7, '아마씨', 180, true, '2026-07-01 12:00:00.000', NULL),
(1700, 7, '햄프씨드', 180, true, '2026-07-01 12:00:00.000', NULL),
(1701, 7, '타히니', 180, true, '2026-07-01 12:00:00.000', NULL),
(1702, 7, '아몬드버터', 180, true, '2026-07-01 12:00:00.000', NULL),
(1703, 8, '꽃소금', 365, true, '2026-07-01 12:00:00.000', NULL),
(1704, 8, '구운소금', 365, true, '2026-07-01 12:00:00.000', NULL),
(1705, 8, '백설탕', 365, true, '2026-07-01 12:00:00.000', NULL),
(1706, 8, '원당', 365, true, '2026-07-01 12:00:00.000', NULL),
(1707, 8, '아카시아꿀', 365, true, '2026-07-01 12:00:00.000', NULL),
(1708, 8, '밤꿀', 365, true, '2026-07-01 12:00:00.000', NULL),
(1709, 8, '조림간장', 365, true, '2026-07-01 12:00:00.000', NULL),
(1710, 8, '화이트와인식초', 365, true, '2026-07-01 12:00:00.000', NULL),
(1711, 8, '엑스트라버진올리브오일', 365, true, '2026-07-01 12:00:00.000', NULL),
(1712, 8, '해바라기유', 365, true, '2026-07-01 12:00:00.000', NULL),
(1713, 8, '옥수수유', 365, true, '2026-07-01 12:00:00.000', NULL),
(1714, 8, '아보카도오일', 365, true, '2026-07-01 12:00:00.000', NULL),
(1715, 8, '굴소스', 365, true, '2026-07-01 12:00:00.000', NULL),
(1716, 8, '피시소스', 365, true, '2026-07-01 12:00:00.000', NULL),
(1717, 8, '참치액', 365, true, '2026-07-01 12:00:00.000', NULL),
(1718, 8, '갈치속젓', 365, true, '2026-07-01 12:00:00.000', NULL),
(1719, 8, '오징어젓', 365, true, '2026-07-01 12:00:00.000', NULL),
(1720, 8, '창난젓', 365, true, '2026-07-01 12:00:00.000', NULL),
(1721, 8, '스리라차소스', 365, true, '2026-07-01 12:00:00.000', NULL),
(1722, 8, '타바스코소스', 365, true, '2026-07-01 12:00:00.000', NULL),
(1723, 8, '우스터소스', 365, true, '2026-07-01 12:00:00.000', NULL),
(1724, 8, '데리야키소스', 365, true, '2026-07-01 12:00:00.000', NULL),
(1725, 8, '돈가스소스', 365, true, '2026-07-01 12:00:00.000', NULL),
(1726, 8, '칠리소스', 365, true, '2026-07-01 12:00:00.000', NULL),
(1727, 8, '핫소스', 365, true, '2026-07-01 12:00:00.000', NULL),
(1728, 8, '고운고춧가루', 365, true, '2026-07-01 12:00:00.000', NULL),
(1729, 8, '굵은고춧가루', 365, true, '2026-07-01 12:00:00.000', NULL),
(1730, 8, '산초가루', 365, true, '2026-07-01 12:00:00.000', NULL),
(1731, 8, '제피가루', 365, true, '2026-07-01 12:00:00.000', NULL),
(1732, 8, '시나몬', 365, true, '2026-07-01 12:00:00.000', NULL),
(1733, 8, '육두구', 365, true, '2026-07-01 12:00:00.000', NULL),
(1734, 8, '카다멈', 365, true, '2026-07-01 12:00:00.000', NULL),
(1735, 8, '타임가루', 365, true, '2026-07-01 12:00:00.000', NULL),
(1736, 8, '로즈마리가루', 365, true, '2026-07-01 12:00:00.000', NULL),
(1737, 8, '비프스톡', 365, true, '2026-07-01 12:00:00.000', NULL),
(1738, 8, '채소스톡', 365, true, '2026-07-01 12:00:00.000', NULL),
(1739, 9, '총각김치', 180, true, '2026-07-01 12:00:00.000', NULL),
(1740, 9, '갓김치', 180, true, '2026-07-01 12:00:00.000', NULL),
(1741, 9, '파김치', 180, true, '2026-07-01 12:00:00.000', NULL),
(1742, 9, '오이소박이', 180, true, '2026-07-01 12:00:00.000', NULL),
(1743, 9, '할라피뇨피클', 180, true, '2026-07-01 12:00:00.000', NULL),
(1744, 9, '올리브절임', 180, true, '2026-07-01 12:00:00.000', NULL),
(1745, 9, '고등어캔', 180, true, '2026-07-01 12:00:00.000', NULL),
(1746, 9, '꽁치캔', 180, true, '2026-07-01 12:00:00.000', NULL),
(1747, 9, '골뱅이캔', 180, true, '2026-07-01 12:00:00.000', NULL),
(1748, 9, '옥수수캔', 180, true, '2026-07-01 12:00:00.000', NULL),
(1749, 9, '토마토캔', 180, true, '2026-07-01 12:00:00.000', NULL),
(1750, 9, '콩통조림', 180, true, '2026-07-01 12:00:00.000', NULL),
(1751, 9, '햄통조림', 180, true, '2026-07-01 12:00:00.000', NULL),
(1752, 9, '런천미트', 180, true, '2026-07-01 12:00:00.000', NULL),
(1753, 9, '사각어묵', 180, true, '2026-07-01 12:00:00.000', NULL),
(1754, 9, '봉어묵', 180, true, '2026-07-01 12:00:00.000', NULL),
(1755, 9, '고기만두', 180, true, '2026-07-01 12:00:00.000', NULL),
(1756, 9, '김치만두', 180, true, '2026-07-01 12:00:00.000', NULL),
(1757, 9, '물만두', 180, true, '2026-07-01 12:00:00.000', NULL),
(1758, 9, '군만두', 180, true, '2026-07-01 12:00:00.000', NULL),
(1759, 9, '너겟', 180, true, '2026-07-01 12:00:00.000', NULL),
(1760, 9, '치킨너겟', 180, true, '2026-07-01 12:00:00.000', NULL),
(1761, 9, '생선가스', 180, true, '2026-07-01 12:00:00.000', NULL),
(1762, 9, '핫도그', 180, true, '2026-07-01 12:00:00.000', NULL),
(1763, 9, '짜장라면', 180, true, '2026-07-01 12:00:00.000', NULL),
(1764, 9, '비빔면', 180, true, '2026-07-01 12:00:00.000', NULL),
(1765, 9, '즉석밥', 180, true, '2026-07-01 12:00:00.000', NULL),
(1766, 9, '카레루', 180, true, '2026-07-01 12:00:00.000', NULL),
(1767, 9, '하이라이스루', 180, true, '2026-07-01 12:00:00.000', NULL),
(1768, 9, '짜장분말', 180, true, '2026-07-01 12:00:00.000', NULL),
(1769, 9, '사과잼', 180, true, '2026-07-01 12:00:00.000', NULL),
(1770, 9, '마멀레이드', 180, true, '2026-07-01 12:00:00.000', NULL),
(1771, 9, '밀크초콜릿', 180, true, '2026-07-01 12:00:00.000', NULL),
(1772, 9, '화이트초콜릿', 180, true, '2026-07-01 12:00:00.000', NULL),
(1773, 9, '비스킷', 180, true, '2026-07-01 12:00:00.000', NULL),
(1774, 9, '콘플레이크', 180, true, '2026-07-01 12:00:00.000', NULL),
(1775, 9, '선식', 180, true, '2026-07-01 12:00:00.000', NULL),
(1776, 10, '코코아가루', 30, true, '2026-07-01 12:00:00.000', NULL),
(1777, 10, '말차가루', 30, true, '2026-07-01 12:00:00.000', NULL),
(1778, 10, '홍차잎', 30, true, '2026-07-01 12:00:00.000', NULL),
(1779, 10, '커피원두', 30, true, '2026-07-01 12:00:00.000', NULL),
(1780, 10, '원두커피', 30, true, '2026-07-01 12:00:00.000', NULL),
(1781, 10, '인스턴트커피', 30, true, '2026-07-01 12:00:00.000', NULL),
(1782, 10, '보리차', 30, true, '2026-07-01 12:00:00.000', NULL),
(1783, 10, '둥굴레차', 30, true, '2026-07-01 12:00:00.000', NULL),
(1784, 10, '결명자차', 30, true, '2026-07-01 12:00:00.000', NULL),
(1785, 10, '루이보스', 30, true, '2026-07-01 12:00:00.000', NULL),
(1786, 10, '히비스커스', 30, true, '2026-07-01 12:00:00.000', NULL),
(1787, 10, '카모마일', 30, true, '2026-07-01 12:00:00.000', NULL),
(1788, 10, '라벤더', 30, true, '2026-07-01 12:00:00.000', NULL),
(1789, 10, '로즈힙', 30, true, '2026-07-01 12:00:00.000', NULL),
(1790, 10, '감초', 30, true, '2026-07-01 12:00:00.000', NULL),
(1791, 10, '홍삼', 30, true, '2026-07-01 12:00:00.000', NULL),
(1792, 10, '마카분말', 30, true, '2026-07-01 12:00:00.000', NULL),
(1793, 10, '스피루리나', 30, true, '2026-07-01 12:00:00.000', NULL),
(1794, 10, '클로렐라', 30, true, '2026-07-01 12:00:00.000', NULL),
(1795, 10, '프로폴리스', 30, true, '2026-07-01 12:00:00.000', NULL),
(1796, 10, '타피오카펄', 30, true, '2026-07-01 12:00:00.000', NULL),
(1797, 10, '우무', 30, true, '2026-07-01 12:00:00.000', NULL),
(1798, 10, '차가버섯', 30, true, '2026-07-01 12:00:00.000', NULL),
(1799, 10, '콜라겐분말', 30, true, '2026-07-01 12:00:00.000', NULL),
(1800, 10, '코코넛칩', 30, true, '2026-07-01 12:00:00.000', NULL),
(1801, 10, '건조코코넛', 30, true, '2026-07-01 12:00:00.000', NULL),
(1802, 10, '바닐라익스트랙', 30, true, '2026-07-01 12:00:00.000', NULL),
(1803, 10, '판한천', 30, true, '2026-07-01 12:00:00.000', NULL),
(1804, 10, '식용색소', 30, true, '2026-07-01 12:00:00.000', NULL)
ON CONFLICT (name) DO NOTHING;

SELECT setval('products_product_id_seq', COALESCE((SELECT MAX(product_id) FROM products), 1));
COMMIT;
