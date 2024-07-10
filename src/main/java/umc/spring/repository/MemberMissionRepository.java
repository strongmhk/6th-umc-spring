package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    MemberMission findByMemberIdAndMissionId(Long memberId, Long missionId);

    @Query("select mm from MemberMission mm join fetch mm.mission where mm.member = :member")
    Page<MemberMission> findAllByMemberJoinFetch(Member member, PageRequest pageRequest);
}
