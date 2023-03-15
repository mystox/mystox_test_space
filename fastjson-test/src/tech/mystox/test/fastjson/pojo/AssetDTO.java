package tech.mystox.test.fastjson.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mystox on 2023/3/14, 18:50.
 * company:
 * description:
 * update record:
 */
public class AssetDTO {

    private List<QueryDTO> queryCI;

    private List<RelationShipDTO> queryRe;
    /**
     * 分页参数，可以不传
     */
    private Integer page;
    /**
     * 分页参数，可以不传
     */
    private Integer pageSize;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public AssetDTO() {
    }

    private AssetDTO(BuilderQueryCI builderQueryCI) {
        this.queryCI =builderQueryCI.queryCI;
        this.queryRe = builderQueryCI.queryRe;
        this.page = builderQueryCI.page;
        this.pageSize = builderQueryCI.pageSize;
    }


    public List<QueryDTO> getQueryCI() {
        return queryCI;
    }

    public void setQueryCI(List<QueryDTO> queryCI) {
        this.queryCI = queryCI;
    }

    public List<RelationShipDTO> getQueryRe() {
        return queryRe;
    }

    public void setQueryRe(List<RelationShipDTO> queryRe) {
        this.queryRe = queryRe;
    }
    public static class BuilderQueryCI{
        private List<QueryDTO> queryCI;

        private List<RelationShipDTO> queryRe;

        private Integer page;

        private Integer pageSize;

        public AssetDTO create() {
            return new AssetDTO(this);
        }

        public BuilderQueryCI() {
        }

        public static BuilderQueryCI startBuild() {
            return new BuilderQueryCI();
        }


        public BuilderQueryCI queryCI(QueryDTO... queryDTO) {

            if(this.queryCI == null){
                this.queryCI = new ArrayList<>();
            }
            queryCI.addAll(Arrays.asList(queryDTO));
            return this;
        }
        public BuilderQueryCI queryRe(RelationShipDTO... relationShipDTO) {

            if(this.queryRe==null){
                this.queryRe = new ArrayList<>();
            }
            queryRe.addAll(Arrays.asList(relationShipDTO));
            return this;
        }


        public BuilderQueryCI page(Integer page) {
            this.page = page;
            return this;
        }


        public BuilderQueryCI pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }
    }
}
