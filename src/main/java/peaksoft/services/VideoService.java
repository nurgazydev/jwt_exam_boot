package peaksoft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.mappers.VideoMapper;
import peaksoft.dto.requests.VideoRequest;
import peaksoft.dto.responses.VideoResponse;
import peaksoft.dto.responses.simpl.SimpleResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Lesson;
import peaksoft.models.Video;
import peaksoft.repositories.LessonRepository;
import peaksoft.repositories.VideoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final LessonRepository lessonRepository;
    private final VideoMapper videoMapper;


    public List<VideoResponse> findAll() {
        List<Video> videos = videoRepository.findAll();
        return videoMapper.convertAllVideosToView(videos);
    }

    public VideoResponse findById(Long id) {
        Video video = getById(id);
        return videoMapper.convertToView(video);
    }

    private Video getById(Long id) {
        return videoRepository.findById(id).orElseThrow(() -> new NotFoundException("task with id: " + id + " not found"));
    }


//    public List<Video> findVideosByLessonId(Long lessonId) {
//        return videoRepository.findVideosByLessonId(lessonId);
//    }


    public VideoResponse save(VideoRequest videoRequest) {
        Video video = videoMapper.convertToEntity(videoRequest);
        Lesson lesson = lessonRepository.findById(videoRequest.getLessonId()).orElseThrow(() -> new NotFoundException("lesson with id: " + videoRequest.getLessonId() + " does not exists"));
        lesson.setVideo(video);
        video.setLesson(lesson);
        Video save = videoRepository.save(video);
        return videoMapper.convertToView(save);
    }


    public SimpleResponse deleteById(Long id) {
        boolean exists = videoRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException("video with id: " + id + " does not exists!");
        }
        videoRepository.deleteById(id);
        return new SimpleResponse(
                "DELETED",
                "video with id: " + id + " deleted successfully"
        );
    }

    public VideoResponse update(Long id, VideoRequest videoRequest) {
        Video video = getById(id);
        Video update = videoMapper.convertToUpdate(video, videoRequest);
        videoRepository.save(update);
        return videoMapper.convertToView(update);
    }
}
