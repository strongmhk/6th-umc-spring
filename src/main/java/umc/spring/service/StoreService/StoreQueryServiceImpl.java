package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.Region;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public Optional<Region> findRegion(Long id) {
        return regionRepository.findById(id);
    }

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {
        Store findStore = findStoreByStoreId(storeId);

        Page<Review> storeReviewPage = reviewRepository.findAllByStore(findStore, PageRequest.of(page, 10));

        return storeReviewPage;
    }

    @Override
    public Page<Mission> getMissionList(Long storeId, Integer page) {
        Store findStore = findStoreByStoreId(storeId);

        Page<Mission> storeMissionPage = missionRepository.findAllByStore(findStore, PageRequest.of(page, 10));

        return storeMissionPage;
    }

    private Store findStoreByStoreId(Long storeId) {
        return storeRepository.findById(storeId).get();
    }
}
