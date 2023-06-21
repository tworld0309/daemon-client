package daemon.mappers;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface SampleMapper {
    List<HashMap> getData();
}