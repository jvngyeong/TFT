package TFT.domain.RiotAPI;

public class LeagueEntryDTO {
    public String puuid;           // Player Universal Unique Identifier
    public String leagueId;        // Not included for the RANKED_TFT_TURBO queueType
    public String summonerId;      // Player's encrypted summonerId
    public String queueType;       // Queue type
    public String ratedTier;       // Only included for the RANKED_TFT_TURBO queueType
    public int ratedRating;        // Only included for the RANKED_TFT_TURBO queueType
    public String tier;            // Not included for the RANKED_TFT_TURBO queueType
    public String rank;            // Player's division within a tier
    public int leaguePoints;       // Not included for the RANKED_TFT_TURBO queueType
    public int wins;               // First placement
    public int losses;             // Second through eighth placement
    public boolean hotStreak;      // Not included for the RANKED_TFT_TURBO queueType
    public boolean veteran;        // Not included for the RANKED_TFT_TURBO queueType
    public boolean freshBlood;     // Not included for the RANKED_TFT_TURBO queueType
    public boolean inactive;       // Not included for the RANKED_TFT_TURBO queueType
    public MiniSeriesDTO miniSeries; // Not included for the RANKED_TFT_TURBO queueType
	public String getPuuid() {
		return puuid;
	}
	public void setPuuid(String puuid) {
		this.puuid = puuid;
	}
	public String getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}
	public String getSummonerId() {
		return summonerId;
	}
	public void setSummonerId(String summonerId) {
		this.summonerId = summonerId;
	}
	public String getQueueType() {
		return queueType;
	}
	public void setQueueType(String queueType) {
		this.queueType = queueType;
	}
	public String getRatedTier() {
		return ratedTier;
	}
	public void setRatedTier(String ratedTier) {
		this.ratedTier = ratedTier;
	}
	public int getRatedRating() {
		return ratedRating;
	}
	public void setRatedRating(int ratedRating) {
		this.ratedRating = ratedRating;
	}
	public String getTier() {
		return tier;
	}
	public void setTier(String tier) {
		this.tier = tier;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public int getLeaguePoints() {
		return leaguePoints;
	}
	public void setLeaguePoints(int leaguePoints) {
		this.leaguePoints = leaguePoints;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public boolean isHotStreak() {
		return hotStreak;
	}
	public void setHotStreak(boolean hotStreak) {
		this.hotStreak = hotStreak;
	}
	public boolean isVeteran() {
		return veteran;
	}
	public void setVeteran(boolean veteran) {
		this.veteran = veteran;
	}
	public boolean isFreshBlood() {
		return freshBlood;
	}
	public void setFreshBlood(boolean freshBlood) {
		this.freshBlood = freshBlood;
	}
	public boolean isInactive() {
		return inactive;
	}
	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}
	public MiniSeriesDTO getMiniSeries() {
		return miniSeries;
	}
	public void setMiniSeries(MiniSeriesDTO miniSeries) {
		this.miniSeries = miniSeries;
	}
    
}
