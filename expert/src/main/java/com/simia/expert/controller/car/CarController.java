package com.simia.expert.controller.car;

import com.simia.expert.service.car.BrandCarService;
import com.simia.expert.service.car.CarService;
import com.simia.expert.service.car.ModelCarService;
import com.simia.expert.service.car.YearCarService;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.expert.car.BrandCarDto;
import com.simia.share.common.model.dto.expert.car.CarDto;
import com.simia.share.common.model.dto.expert.car.ModelCarDto;
import com.simia.share.common.model.dto.expert.car.YearCarDto;
import com.simia.share.common.model.response.MapResponse;
import com.simia.share.common.util.SecurityUtil;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.simia.share.common.exception.messages.CommonExceptionMessage.USER_IS_ANONYMOUS;

/**
 * @author vitalij
 * @version 1.0
 */
@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private BrandCarService brandCarService;

    @Autowired
    private ModelCarService modelCarService;

    @Autowired
    private YearCarService yearCarService;

    @GetMapping("/{id}")
    public CarDto getById(@PathVariable("id") UUID id) {
        return carService.getById(id);
    }

    @GetMapping("/user")
    public List<CarDto> getAll() {
        UUID userId = SecurityUtil.getUserId();
        if (userId == null) {
            throw new ClientException(USER_IS_ANONYMOUS);
        }
        return ListUtils.emptyIfNull(carService.getAllByUserId(userId));
    }

    @GetMapping("/user/{userId}")
    public List<CarDto> getAllById(@PathVariable("userId") UUID userId) {
        return ListUtils.emptyIfNull(carService.getAllByUserId(userId));
    }

    @GetMapping("/user/as-worker")
    public List<CarDto> getCarAsBusiness(@RequestParam("clientId") UUID clientId, @RequestParam("corporationId") UUID corporationId) {
        return carService.getAsWorker(clientId, corporationId);
    }

    @PostMapping
    public CarDto create(@RequestBody @Valid  CarDto car) {
        return carService.create(car);
    }

    @PostMapping("/set-favorite/{idCar}")
    public CarDto setFavorite(@PathVariable("idCar") UUID idCar) {
        return carService.setFavorite(idCar);
    }

    @PostMapping("/service/{idCar}/{idService}")
    public MapResponse addService(@PathVariable("idCar") UUID idCar, @PathVariable("idService") UUID idService) {
        carService.addService(idCar, idService);
        return new MapResponse("true");
    }

    @DeleteMapping("/remove/service/{idCar}/{idService}")
    public MapResponse removeService(@PathVariable("idCar") UUID idCar, @PathVariable("idService") UUID idService) {
        carService.removeService(idCar, idService);
        return new MapResponse("true");
    }

    @PostMapping("/filter-attribute/{idCar}/{idAttribute}")
    public MapResponse addFilterAttribute(@PathVariable("idCar") UUID idCar, @PathVariable("idAttribute") UUID idAttribute) {
        carService.addFilterAttribute(idCar, idAttribute);
        return new MapResponse("true");
    }

    @DeleteMapping("/remove/filter-attribute/{idCar}/{idAttribute}")
    public MapResponse removeFilterAttribute(@PathVariable("idCar") UUID idCar, @PathVariable("idAttribute") UUID idAttribute) {
        carService.removeFilterAttribute(idCar, idAttribute);
        return new MapResponse("true");
    }

    @PutMapping
    public CarDto update(@RequestBody  @Valid CarDto car) {
        return carService.update(car);
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        carService.delete(id);
        return new MapResponse("true");
    }

    @DeleteMapping("/all-by-user")
    public MapResponse deleteByUserId() {
        carService.deleteByUserId(SecurityUtil.getUserId());
        //TODO: notify other service
        return new MapResponse("true");
    }

    @GetMapping("/brands")
    public List<BrandCarDto> getAllBrands() {
        return brandCarService.getAll();
    }

    @GetMapping("/models")
    public List<ModelCarDto> getAllModels() {
        return modelCarService.getAll();
    }

    @GetMapping("/models/by-brand/{id}")
    public List<ModelCarDto> getAllModelsByBrandId(@PathVariable("id") UUID id) {
        return modelCarService.getAllByBrandId(id);
    }

    @GetMapping("/years")
    public List<YearCarDto> getAllYears() {
        return yearCarService.getAll();
    }
}
