var Localize = require("localize-with-spreadsheet");
var transformer = Localize.fromGoogleSpreadsheet("16jpaSKc-lA9p7h6XreR-bF4EEqwJ2gJmmjJMLQZ334U", '*');
transformer.setKeyCol('KEY');

transformer.save("../../../../../res/values-ko/strings.xml", { valueCol: "ko", format: "android" });
transformer.save("../../../../../res/values/strings.xml", { valueCol: "en", format: "android" });
transformer.save("../../../../../res/values-ja/strings.xml", { valueCol: "jp", format: "android" });
transformer.save("../../../../../res/values-zh/strings.xml", { valueCol: "chinese", format: "android" });
transformer.save("../../../../../res/values-fr/strings.xml", { valueCol: "fr", format: "android" });