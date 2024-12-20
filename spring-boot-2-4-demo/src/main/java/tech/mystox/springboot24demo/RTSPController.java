package tech.mystox.springboot24demo;

import org.apache.commons.io.IOUtils;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.bytedeco.ffmpeg.global.avcodec.AV_CODEC_ID_AAC;
import static org.bytedeco.ffmpeg.global.avcodec.AV_CODEC_ID_H264;
import static org.bytedeco.ffmpeg.global.avutil.AV_PIX_FMT_YUV420P;

/**
 * Created by mystox on 2023/5/11, 15:44.
 * company:
 * description:
 * update record:
 */
@RestController
@CrossOrigin
public class RTSPController {
    private static final DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
    private static final int BUFFER_SIZE = 1024 * 1024; // 1MB


    final ExecutorService executorService = Executors.newCachedThreadPool();

    //        final ScheduledExecutorService executorService1 = Executors.newSingleThreadScheduledExecutor();
    @GetMapping(value = "/rtsp-to-flv", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public Flux<DataBuffer> rtspToFlv() {

        CountDownLatch count = new CountDownLatch(1);

        return Flux.create((FluxSink<ByteBuffer> emitter) -> {
                    try (final ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                        //                        FFmpegFrameGrabber aDefault = FFmpegFrameGrabber.createDefault("rtsp://admin:itime1234@172.20.80.151:554/Streaming/Channels/101");
//                        String rtsp = "rtsp://admin:itime!QAZ@172.20.80.151:554/Streaming/Channels/101";
                        String rtsp = "rtsp://admin:itime123456@172.20.80.151:554/Streaming/Channels/101";
//                        http://172.18.71.36:50200/rtsp-to-flv/record/stream_101.flv?starTime=1684142911212&endTime=1684146532634
                        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(rtsp) {{
                            setFormat("rtsp");
                            setOption("rtsp_transport", "tcp");
                            setVideoCodec(AV_CODEC_ID_H264); //指定视频编解码器
                        }};
                        grabber.start();
                        avutil.av_log_set_level(avutil.AV_LOG_QUIET);
                        FFmpegLogCallback.set();
                        //                        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputStream, grabber.getImageWidth(), grabber.getImageHeight(), 0);
                        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("rtmp://172.21.150.185:1935/live/mystream", grabber.getImageWidth(), grabber.getImageHeight(), 0);

                        recorder.setFormat("flv");
                        recorder.setVideoCodec(AV_CODEC_ID_H264);
                        recorder.setAudioCodec(AV_CODEC_ID_AAC);
                        recorder.setAudioBitrate(grabber.getAudioBitrate());
                        recorder.setSampleRate(grabber.getSampleRate());
                        recorder.setAudioChannels(grabber.getAudioChannels());
                        recorder.setFrameRate(grabber.getFrameRate());
                        recorder.start();
                        //                        FFmpegFrameGrabber finalGrabber = grabber;
                        //                        FFmpegFrameRecorder finalRecorder = recorder;
                        Runnable runnable = () -> {
                            try {
                                Frame frame;
                                while ((frame = grabber.grabFrame()) != null && count.getCount() > 0) {
                                    recorder.record(frame);
                                    if (outputStream.size() > 0) {
                                        byte[] array = outputStream.toByteArray();
                                        emitter.next(ByteBuffer.wrap(array));
                                        outputStream.reset();
                                    }
                                }
                                System.out.println("任务关闭成功！！！！！！！！！");
                            } catch (FrameGrabber.Exception | FrameRecorder.Exception e) {
                                emitter.error(e);
                            } finally {
                                System.out.println("资源释放");
                                try {
                                    grabber.stop();
                                    recorder.stop();
                                    emitter.complete();
                                } catch (FFmpegFrameGrabber.Exception | FFmpegFrameRecorder.Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        };
                        executorService.execute(runnable);
                    } catch (FrameGrabber.Exception | FrameRecorder.Exception e) {
                        emitter.error(e);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }, FluxSink.OverflowStrategy.BUFFER)
                .map(dataBufferFactory::wrap)
                //                .doOnCancel(executorService::shutdownNow)
                .doOnCancel(count::countDown)
                .doFinally(signalType -> {
                    System.out.println("停止任务！！！！！！！！" + signalType);
                    count.countDown();
                    System.out.println("停止任务！！！！！！！！" + executorService.isShutdown());
                });
    }

    @GetMapping(value = "/rtsp-to-flv2", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public Flux<DataBuffer> rtspToFlv2() {
        Flux<DataBuffer> objectFlux = Flux.create((FluxSink<ByteBuffer> emitter) -> {
                    try {
                        //                        FFmpegFrameGrabber aDefault = FFmpegFrameGrabber.createDefault("rtsp://admin:itime1234@172.20.80.151:554/Streaming/Channels/101");
//                        final String filename = "rtsp://admin:itime!QAZ@172.20.80.151:554/Streaming/Channels/101";
                        final String filename = "rtsp://admin:itime!QAZ@172.20.80.151:554/Streaming/tracks/101(?starttime=20230515t172831z&endtime=20230515t182852z)";
                        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filename) {{
                            setFormat("rtsp");
                            setOption("rtsp_transport", "tcp");
                        }};
                        grabber.setVideoCodec(AV_CODEC_ID_H264); // 指定视频编解码器
                        grabber.start();
                        //                        avutil.av_log_set_level(avutil.AV_LOG_QUIET);
                        //                        FFmpegLogCallback.set();
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        byte[] bytes = outputStream.toByteArray();
                        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputStream, grabber.getImageWidth(), grabber.getImageHeight(), 0);
                        recorder.setFormat("mp4");
                        recorder.setAudioCodec(grabber.getAudioCodec());
                        recorder.setAudioBitrate(grabber.getAudioBitrate());
                        recorder.setSampleRate(grabber.getSampleRate());
                        recorder.setAudioChannels(grabber.getAudioChannels());
                        recorder.setFrameRate(grabber.getFrameRate());
                        recorder.start();

                        executorService.execute(() -> {
                            try {
                                Frame frame;
                                while ((frame = grabber.grabFrame()) != null) {
                                    //                                    int length1 = frame.data.array().length;
                                    recorder.record(frame);
                                    //                                    System.out.println(length1);
                                    try (PipedInputStream in = new PipedInputStream();
                                         final PipedOutputStream out = new PipedOutputStream(in)) {
                                        System.out.println(outputStream.size());
                                        outputStream.writeTo(out);
                                        byte[] bytes1 = IOUtils.toByteArray(in);
                                        int length = bytes1.length;
                                        System.out.println(length);
                                        emitter.next(ByteBuffer.wrap(bytes1));
                                        System.out.println(in.read());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                grabber.stop();
                                recorder.stop();
                                emitter.complete();
                            } catch (FrameGrabber.Exception | FrameRecorder.Exception e) {
                                emitter.error(e);
                            }
                        });
                    } catch (FrameGrabber.Exception | FrameRecorder.Exception e) {
                        emitter.error(e);
                    }
                }, FluxSink.OverflowStrategy.BUFFER)
                .map(dataBufferFactory::wrap)
                .doOnCancel(executorService::shutdownNow);
        return objectFlux;
    }

    public static void main(String[] args) throws FrameGrabber.Exception, FFmpegFrameRecorder.Exception, InterruptedException {
        //                        FFmpegFrameGrabber aDefault = FFmpegFrameGrabber.createDefault("rtsp://admin:itime1234@172.20.80.151:554/Streaming/Channels/101");
                 SimpleDateFormat sdfT = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdfZ = new SimpleDateFormat("HHmmss");
        final Date startTime = new Date(System.currentTimeMillis()-720000);
        String startTimeS = sdfT.format(startTime)+"t"+sdfZ.format(startTime)+"z";
        final Date entTime = new Date(System.currentTimeMillis()-710000);
        String endTimeS = sdfT.format(entTime) + "t" + sdfZ.format(entTime) + "z";
        final String filename = "rtsp://admin:itime!QAZ@172.20.80.151:554/Streaming/tracks/101(?starttime=" + startTimeS + "&endtime=" + endTimeS + ")";
        System.out.println(filename);
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filename) {{
            setFormat("rtsp");
            setOption("rtsp_transport", "tcp");
        }};
        grabber.setVideoCodec(AV_CODEC_ID_H264); // 指定视频编解码器
        grabber.start();
                                avutil.av_log_set_level(avutil.AV_LOG_QUIET);
                                FFmpegLogCallback.set();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        byte[] bytes = outputStream.toByteArray();
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("record6.mp4", grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
        // 文件格式
        recorder.setFormat("mp4");

        // 帧率与抓取器一致
        recorder.setFrameRate(grabber.getFrameRate());

        // 编码格式
        recorder.setPixelFormat(AV_PIX_FMT_YUV420P);

        // 编码器类型
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_MPEG4);

        // 视频质量，0表示无损
        recorder.setVideoQuality(0);

        // 初始化
        recorder.start();

//        Frame frame;
//        while ((frame = grabber.grabFrame()) != null) {
//            //                                    int length1 = frame.data.array().length;
//            System.out.println("111111111111111111111");
//            recorder.record(frame);
//            //                                    System.out.println(length1);
////            try (PipedInputStream in = new PipedInputStream();
////                  final PipedOutputStream out = new PipedOutputStream(in)) {
////                System.out.println(outputStream.size());
////                outputStream.writeTo(out);
////                byte[] bytes1 = IOUtils.toByteArray(in);
////                int length = bytes1.length;
////                System.out.println(length);
////                System.out.println(in.read());
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//        }
        int nullNumber = 0;
        int r = 0;
        boolean runing = true;
        while (runing) {
            // 抓取一帧
            Frame f = grabber.grab();
            if (f != null) {
                try {
                    // 转码
                    recorder.record(f);
                    System.out.println(r++);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                nullNumber++;
                if (nullNumber > 200) {
                    break;
                }
            }
            Thread.sleep(5);
        }
        System.out.println(outputStream.size());
        grabber.stop();
        recorder.stop();
    }
}
