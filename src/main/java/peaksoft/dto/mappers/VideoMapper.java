package peaksoft.dto.mappers;

import org.springframework.stereotype.Component;
import peaksoft.dto.requests.VideoRequest;
import peaksoft.dto.responses.VideoResponse;
import peaksoft.models.Video;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class VideoMapper {

    public Video convertToEntity(VideoRequest videoRequest) {
        if (videoRequest == null) {
            return null;
        }
        Video video = new Video();
        video.setVideoName(videoRequest.getVideoName());
        video.setLink(videoRequest.getLink());
        video.setCreatedAt(LocalDate.now());
        return video;
    }

    public VideoResponse convertToView(Video video) {
        if (video == null) {
            return null;
        }
        VideoResponse videoResponse = new VideoResponse();
        videoResponse.setId(video.getId());
        videoResponse.setVideoName(video.getVideoName());
        videoResponse.setLink(video.getLink());
        videoResponse.setCreatedAt(LocalDate.now());
        return videoResponse;
    }

    public List<VideoResponse> convertAllVideosToView(List<Video> videos) {
        List<VideoResponse> videoResponses = new ArrayList<>();
        for (Video video : videos) {
            videoResponses.add(convertToView(video));
        }
        return videoResponses;
    }

    public Video convertToUpdate(Video video, VideoRequest videoRequest) {
        video.setVideoName(videoRequest.getVideoName());
        video.setLink(videoRequest.getLink());
        video.setCreatedAt(LocalDate.now());
        return video;
    }
}
