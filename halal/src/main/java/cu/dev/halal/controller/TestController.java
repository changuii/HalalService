//package cu.dev.halal.controller;
//
//
//import com.google.gson.JsonObject;
//import cu.dev.halal.service.AddressService;
//import cu.dev.halal.service.DataService;
//import org.apache.commons.httpclient.URIException;
//import org.json.simple.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.util.Map;
//

// //개발용 테스트 엔드포인트
//@RestController
//@RequestMapping("/test")
//public class TestController {
//
//    private final AddressService addressService;
//    private final DataService dataService;
//
//
//
//    public TestController(
//            @Autowired AddressService addressService,
//            @Autowired DataService dataService
//    ){
//        this.dataService = dataService;
//        this.addressService = addressService;
//    }
//
//    @PostMapping
//    public ResponseEntity<String> toCoordinate(
//            @RequestParam String address
//    ){
//        return ResponseEntity.status(200).body(this.addressService.toCoordinate(address).toString());
//    }
//
//    @GetMapping
//    public ResponseEntity<?> distance(
//            @RequestParam("x1") double x1,
//            @RequestParam("y1") double y1,
//            @RequestParam("x2") double x2,
//            @RequestParam("y2") double y2
//            ){
//
//        Double distance = this.addressService.dis(x1, y1, x2, y2);
//        return ResponseEntity.status(200).body(distance);
//    }
//
//    @GetMapping("/testtest")
//    public ResponseEntity<Map> test() throws IOException, URISyntaxException {
//        Map jsonObject = this.dataService.getData();
//
//        return ResponseEntity.status(200).body(jsonObject);
//    }
//    @GetMapping("/test1")
//    public ResponseEntity<?> test1(
//            @RequestParam("x") Double x,
//            @RequestParam("y") Double y
//    ){
//        String addr = this.addressService.toAddress(x, y);
//        return ResponseEntity.status(200).body(addr);
//    }
//
//}
